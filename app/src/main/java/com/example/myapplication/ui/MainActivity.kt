package com.example.myapplication.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.myapplication.datasource.MyApplicationRoomDatabase
import com.example.myapplication.model.StudentViewModel
import androidx.compose.runtime.collectAsState
//import com.example.myapplication.ui.student.PlanScreenTopLevel
import com.example.myapplication.ui.student.StudentScreen
import androidx.compose.runtime.getValue
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.navigation.Screen
import com.example.myapplication.ui.plan.PlanScreen


class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyApplicationRoomDatabase::class.java,
            "myapplication_database.db"
        ).build()
    }
    private val viewModel by viewModels<StudentViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return StudentViewModel(db.studentDao()) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val state by viewModel.state.collectAsState()

            }
                    BuildNavigationGraph()
                }
            }
        }

@Composable
private fun BuildNavigationGraph(
    studentViewModel: StudentViewModel = viewModel()
) {
    // The NavController is in a place where all
    // our composables can access it.
    val navController = rememberNavController()

    // Each NavController is associated with a NavHost.
    // This links the NavController with a navigation graph.
    // As we navigate between composables the content of
    // the NavHost is automatically recomposed.
    // Each composable destination in the graph is associated with a route.
    NavHost(
        navController = navController,
        startDestination = Screen.Plan.route
    ) {
        composable(Screen.Plan.route) { PlanScreen(navController) }
        composable(Screen.Student.route) { StudentScreen(navController)
        } }
//        composable(Screen.Login.route) { LoginScreen(navController) }
    }

//
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // zdjecia do plan
//
//        val toolbar = binding.toolbar
//        setSupportActionBar(toolbar)
//
//        val drawer = binding.drawerLayout
//        val toggle = ActionBarDrawerToggle(
//            this,
//            drawer,
//            toolbar,
//            R.string.nav_open_drawer,
//            R.string.nav_close_drawer
//        )
//
//        drawer.addDrawerListener(toggle)
//        toggle.syncState()
//
//        val navView = binding.bottomNavView
//
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_student, R.id.navigation_nav_plan
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//    }
//
//
//}