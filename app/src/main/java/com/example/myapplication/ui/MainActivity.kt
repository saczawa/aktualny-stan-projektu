package com.example.myapplication.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.myapplication.datasource.MyApplicationRoomDatabase
import com.example.myapplication.model.StudentViewModel
import androidx.compose.runtime.collectAsState
import com.example.myapplication.ui.student.StudentsScreen
import androidx.compose.runtime.getValue


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
            RoomGuideAndroidTheme {
                val state by viewModel.state.collectAsState()
                StudentsScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
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