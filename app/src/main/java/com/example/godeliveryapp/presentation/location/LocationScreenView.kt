package com.example.godeliveryapp.presentation.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.LocationCardModel
import com.example.godeliveryapp.domain.model.LocationDetails
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private var locationCallback: LocationCallback? = null
private var locationRequired = false

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreenView(
    modifier: Modifier = Modifier,
    navController: NavController,
    locationScreenViewModel: LocationScreenViewModel = hiltViewModel()
) {

    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val filteredLocations by locationScreenViewModel.filteredList.collectAsState(initial = emptyList())
    val context = LocalContext.current
    var fusedLocationClient by remember { mutableStateOf<FusedLocationProviderClient?>(null) }
    var currentLocation by remember { mutableStateOf(LocationDetails(0.toDouble(), 0.toDouble())) }

    var textFieldValue by remember { mutableStateOf("") }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    val locationModel by locationScreenViewModel.locationModel.collectAsState()
    LaunchedEffect(textFieldValue) {
        locationScreenViewModel.filterLocations(textFieldValue)
    }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            locationRequired = true
            startLocationUpdates(context, fusedLocationClient!!)
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if (permissions.all {
                ContextCompat.checkSelfPermission(
                    context,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            startLocationUpdates(context, fusedLocationClient!!)

        } else {
            launcherMultiplePermissions.launch(permissions)
        }
    }

    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                fusedLocationClient!!.removeLocationUpdates(this)
                currentLocation = LocationDetails(lo.latitude, lo.longitude)
                locationScreenViewModel.getNearbyLocations("${currentLocation.latitude},${currentLocation.longitude}")
                locationCallback = null
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(NormalPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(screenHeight / 12))

            Box(
                modifier = Modifier
                    .height(screenHeight / 2.5f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.delivery_location_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(screenHeight / 8))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Grant current location",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(ExtraSmallPadding2))

                Box(modifier = Modifier.width(screenWidth / 1.5f)) {
                    Text(
                        text = "This let us show nearby restaurants, stores you can order from",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(screenHeight / 16))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Spacer(modifier = Modifier.height(MediumPadding1))

                TextButton(
                    onClick = {

                        val encodedModel = Json.encodeToString(locationModel)
                        navController.navigate("address_screen/$encodedModel")
                        Log.d("LocationScreenView", "Current Location: $currentLocation")
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight / 14),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = colorResource(id = R.color.black),
                    )
                ) {

                    Text(
                        text = "Use current location",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = colorResource(id = R.color.white)
                    )

                }

                Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                TextButton(
                    onClick = { showBottomSheet = !showBottomSheet },
                    border = BorderStroke(0.5.dp, colorResource(id = R.color.black)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight / 14),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = colorResource(id = R.color.white),
                    )
                ) {

                    Text(
                        text = "Enter manually",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = colorResource(id = R.color.black)
                    )

                }

                Spacer(modifier = Modifier.padding(NormalPadding))


            }

            if (showBottomSheet) {

                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    dragHandle = ({}),
                    containerColor = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((screenHeight)),
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                NormalPadding
                            ),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        item {

                            Spacer(modifier = Modifier.height(NormalPadding))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Search for location",
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = colorResource(
                                        id = R.color.black
                                    ),
                                )

                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.black),
                                    modifier = Modifier
                                        .scale(1f)
                                        .clickable { showBottomSheet = !showBottomSheet }
                                )
                            }

                            Spacer(modifier = Modifier.height(NormalPadding))

                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Search
                                ),
                                keyboardActions = KeyboardActions(
                                    onSearch = {
                                        locationScreenViewModel.filterLocations(textFieldValue)
                                    }
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Search,
                                        contentDescription = null,
                                        tint = Color.DarkGray
                                    )
                                },
                                placeholder = {
                                    Text(
                                        text = "e.g. Panchkula, Haryana",
                                        color = Color.Gray,
                                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                                        textAlign = TextAlign.Start,
                                    )
                                },
                                maxLines = 1,
                                singleLine = true,
                                value = textFieldValue,
                                onValueChange = { textFieldValue = it },
                                shape = RoundedCornerShape(5.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = Color.LightGray,
                                    focusedBorderColor = colorResource(id = R.color.black),
                                    focusedTextColor = colorResource(id = R.color.black),
                                    cursorColor = colorResource(id = R.color.black),
                                    errorCursorColor = Color.Red,
                                    errorBorderColor = Color.Red,
                                    unfocusedTextColor = colorResource(id = R.color.black)
                                )

                            )

                            Spacer(modifier = Modifier.height(MediumPadding1))
                        }

                        if (textFieldValue != "") {
                            if (filteredLocations != null) {
                                items(filteredLocations!!.size) { index ->

                                    val locationCard = filteredLocations!![index]

                                    LocationCard(
                                        locationCardModel = locationCard,
                                        onClick = {
                                            val encodedModel = Json.encodeToString(locationCard)
                                            navController.navigate("address_screen/$encodedModel")
                                            locationScreenViewModel.selectLocation(locationCard)
                                        }
                                    )

                                    Spacer(modifier = Modifier.height(MediumPadding2))

                                }
                            }
                        }

                    }

                }

            }


        }
    }

}

@Composable
fun LocationCard(
    locationCardModel: LocationCardModel,
    onClick: (() -> Unit)? = null
) {

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick?.invoke()
                },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {

            Icon(
                modifier = Modifier.scale(1.2f),
                imageVector = Icons.Outlined.LocationOn, contentDescription = null,
                tint = Color.DarkGray
            )

            Spacer(modifier = Modifier.width(ExtraSmallPadding3))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    locationCardModel.title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                    color = colorResource(id = R.color.black)
                )

                Spacer(modifier = Modifier.height(ExtraSmallPadding2))

                locationCardModel.address.label?.let {
                    Text(
                        text = it,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Clip,
                        maxLines = 2
                    )
                }
            }

            Spacer(modifier = Modifier.width(ExtraSmallPadding1))


        }
    }


}

@SuppressLint("MissingPermission")
private fun startLocationUpdates(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient
) {

    if (!isLocationEnabled(context)) {
        Toast.makeText(context, "Please enable location services", Toast.LENGTH_SHORT).show()
        return
    }

    locationCallback?.let {
        val locationRequest = LocationRequest.create().apply {

        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            it,
            Looper.getMainLooper()
        )
    }
}


private fun isLocationEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
}