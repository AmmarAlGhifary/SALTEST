package com.example.saltest.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.saltest.data.viewmodel.HomeViewModel
import com.example.saltest.ui.theme.SALTESTTheme
import com.example.saltest.view.home.HomeListItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SALTESTTheme {
                CallApi()
            }
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterialApi
@Composable
fun CallApi(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val getAllUserData = viewModel.getUserData.observeAsState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF6200EE))
                        .padding(15.dp)
                ) {
                    Text(
                        text = "Salt Test",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White

                    )
                }

                scope.launch {
                    val result = viewModel.getUserData()
                    Toast.makeText(context, "Error: ${result.message}", Toast.LENGTH_SHORT).show()
                }

                if (!viewModel.isLoading.value) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }

                if (viewModel.isLoading.value) {
                    if (viewModel.getUserData.value!!.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            items(getAllUserData.value!!.size) { index ->
                                HomeListItem(getAllUserData.value!![index])
                            }
                        }
                    }
                }
            }
        }
    }
}