import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobilcash.atmlight.navigation.Destinations
import com.mobilcash.atmlight.ui.SignatureScreen
import com.mobilcash.atmlight.ui.account_selection.AccountTypeSelectionScreen
import com.mobilcash.atmlight.ui.feedback.ThanksFeedbackScreen
import com.mobilcash.atmlight.ui.login.EnterPinScreen
import com.mobilcash.atmlight.ui.print_voucher.PrintReceiptScreen
import com.mobilcash.atmlight.ui.print_voucher.QRReceiptScreen
import com.mobilcash.atmlight.ui.processing_transaction.ProcessingTransactionScreen
import com.mobilcash.atmlight.ui.select_amount.WithdrawalAmountScreen
import com.mobilcash.atmlight.ui.transaction_approved.TransactionApprovedScreen
import com.mobilcash.atmlight.ui.transaction_selection.TransactionSelectionScreen
import com.mobilcash.atmlight.ui.welcome.InsertCardScreen

@Composable
fun AppNavigation(startingIntent: Intent?, modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.WELCOME
    ) {
        composable(Destinations.SPLASH) {
            SplashScreen(navController)
        }
        composable(Destinations.WELCOME) { backStackEntry ->
            InsertCardScreen(navController, startingIntent)
        }
        composable(Destinations.ENTER_PIN) {
            EnterPinScreen(navController)
        }
        composable(Destinations.SELECTION_TRANSACTION) {
            TransactionSelectionScreen(navController)
        }
        composable(Destinations.SELECT_ACCOUNT_TYPE) {
            AccountTypeSelectionScreen(navController)
        }
        composable(Destinations.SELECT_WITHDRAWAL_AMOUNT) {
            WithdrawalAmountScreen(navController)
        }
        composable(Destinations.WITHDRAWALS) {
            TransactionSelectionScreen(navController)
        }
        composable(Destinations.PROCESSING_TRANSACTION_SCREEN) {
            ProcessingTransactionScreen(navController)
        }
        composable(Destinations.SIGNATURE_SCREEN) {
            SignatureScreen(navController)
        }
        composable(Destinations.APPROVED_TRANSACTION) {
            TransactionApprovedScreen(navController)
        }
        composable(Destinations.PRINT_VOUCHER) {
            PrintReceiptScreen(navController)
        }
        composable(Destinations.QR_RECEIPT_SCREEN) {
            QRReceiptScreen(navController)
        }
        composable(Destinations.THANKS_FEEDBACK_SCREEN) {
            ThanksFeedbackScreen(navController)
        }
    }
}
