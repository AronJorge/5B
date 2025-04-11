import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import com.mobilcash.atmlight.ui.welcome.InsertCardScreen


@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(true) {
        delay(3000L)
        navController.navigate("welcome") {
            popUpTo("splash") { inclusive = true }
        }
    }

}