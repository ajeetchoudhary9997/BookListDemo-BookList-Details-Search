package com.example.booklistcomposedemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.booklistcomposedemo.presentaion.bookList.BookListCore
import com.example.booklistcomposedemo.presentaion.bookdetails.BookDetailsCore
import com.example.booklistcomposedemo.presentaion.booksearch.BookSearchCore
import com.example.booklistcomposedemo.ui.theme.MyComposeTheme
import kotlinx.serialization.Serializable

@Serializable
object BookListScreen

@Serializable
data class DetailsScreen(val id: String)

@Serializable
object SearchScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComposeTheme() {
                val myNavController = rememberNavController()
                Scaffold { innerPadding ->
                    NavHost(
                        myNavController, startDestination = BookListScreen, modifier = Modifier.padding(innerPadding),
                        builder = {
                            composable<BookListScreen> {
                                BookListCore(myNavController = myNavController)
                            }
                            composable<DetailsScreen> {
                                val args = it.toRoute<DetailsScreen>()
                                BookDetailsCore(id = args.id)
                            }
                            composable<SearchScreen> {
                                BookSearchCore(myNavController = myNavController)
                            }
                        },
                    )
                }
            }
        }
    }
}
