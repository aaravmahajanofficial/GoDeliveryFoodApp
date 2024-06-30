package com.example.godeliveryapp.presentation.navigation

sealed class Route(val route: String) {
    data object DetailsScreen : Route(route = "details_screen")

    data object CategoryScreen : Route(route = "category_screen")

    data object HomeScreen : Route(route = "home_screen")
    data object CartScreen : Route(route = "cart_screen/{coupon_code}")

    data object LoginPage : Route(route = "login_page")

    data object SignUpPage : Route(route = "sign_up_page")

    data object WelcomeScreen : Route(route = "welcome_screen")

    data object ForgotPasswordScreen : Route(route = "forgot_password_screen")

    data object OtpScreen : Route(route = "otp_screen/{email}")

    data object CreateNewPasswordScreen : Route(route = "create_new_password_screen")

    data object ProfileScreen : Route(route = "profile_screen")

    data object OrderScreen : Route(route = "order_screen")

    data object OrderSuccessScreen : Route(route = "order_success_screen")

    data object MyOrdersScreen : Route(route = "my_orders_screen")

    data object MyOrderDetailScreen : Route(route = "my_order_detail_screen")

    data object MyFavouritesScreen : Route(route = "my_favourites_screen")

    data object OnBoardingScreen : Route(route = "on_boarding_screen")

    data object SearchScreen : Route(route = "search_screen")

    data object PromoCodeScreen : Route(route = "promo_code_screen")

    data object AddressScreen : Route(route = "address_screen")

}