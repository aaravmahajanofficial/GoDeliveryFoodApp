package com.example.godeliveryapp.presentation.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.MyLocation
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.LocationCardModel
import com.example.godeliveryapp.domain.model.LocationDetails
import com.example.godeliveryapp.presentation.Dimens
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding1
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CustomTextButton
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

private var locationCallback: LocationCallback? = null
private var locationRequired = false

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationScreenViewModel = hiltViewModel()
) {
    val nearbyLocationsCards =
        viewModel.nearbyLocationsCards.collectAsState(initial = listOf()).value
    val context = LocalContext.current
    var fusedLocationClient by remember {
        mutableStateOf<FusedLocationProviderClient?>(null)
    }
    var currentLocation by remember {
        mutableStateOf(LocationDetails(0.toDouble(), 0.toDouble()))
    }

    var filteredLocations by remember {
        mutableStateOf(listOf<LocationCardModel>())
    }

    fun filterLocations(query: String) {

        if (nearbyLocationsCards != null) {
            filteredLocations = nearbyLocationsCards.filter {
                it.address.label.contains(query, ignoreCase = true)
            }
        }

    }

    var textValue by remember {
        mutableStateOf("")
    }

    LaunchedEffect(textValue) {

        filterLocations(textValue)

    }

    val screenHeight = LocalConfiguration.current.screenHeightDp

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    fun onClick() {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = true
            }
        }
    }

    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                fusedLocationClient!!.removeLocationUpdates(this)
                currentLocation = LocationDetails(lo.latitude, lo.longitude)
                viewModel.getNearbyLocations("${currentLocation.latitude},${currentLocation.longitude}")
                locationCallback = null
            }
        }
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(NormalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Spacer(modifier = Modifier.height((screenHeight / 10).dp))

        Box(
            modifier = Modifier
                .height((screenHeight / 3).dp)
                .padding(NormalPadding), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.delivery_location_svg),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(120.dp))

        Box(
            modifier = Modifier
                .padding(Dimens.MediumPadding2)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Grant current location",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(218.dp),
                    text = "This let us show nearby restaurants, stores you can order from",
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Clip
                )

//                Text(text = nearbyLocationsCards?.firstOrNull()?.title.toString())
//                Text(
//                    text = "${currentLocation.latitude.toString()},${currentLocation.longitude.toString()}"
//                )
            }
        }

        Spacer(modifier = Modifier.height(NormalPadding))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween

        ) {

            val permissions = arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )

            CustomTextButton(
                buttonText = "Use current location",
                textColorId = R.color.white,
                buttonColorId = R.color.black,
                fontWeight = FontWeight.Medium,
                onClick = {

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
            )
            Spacer(modifier = Modifier.height(ExtraSmallPadding3))
            CustomTextButton(
                buttonText = "Enter manually",
                textColorId = R.color.black,
                buttonColorId = R.color.lightGray,
                fontWeight = FontWeight.SemiBold,
                onClick = { onClick() }
            )
        }

        if (showBottomSheet) {

            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                dragHandle = ({}),
                containerColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight).dp),
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            NormalPadding
                        ),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    item {
                        Box(modifier = Modifier
                            .clickable {
                                showBottomSheet = false
                            }
                            .background(
                                color = Color.White,
                                shape = CircleShape
                            ),
                            contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = null,
                                tint = colorResource(id = R.color.black),
                                modifier = Modifier.scale(1f),
                            )
                        }

                        Spacer(modifier = Modifier.height(ExtraSmallPadding3))

                        Text(
                            text = "Search for location",
                            color = colorResource(id = R.color.black),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                        )

                        Spacer(modifier = Modifier.height(NormalPadding))

                        OutlinedTextField(
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            value = textValue,
                            onValueChange = { textValue = it },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = null,
                                    tint = Color.DarkGray
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedContainerColor = colorResource(
                                    id = R.color.lightGray
                                ),
                                unfocusedContainerColor = colorResource(id = R.color.lightGray)
                            ),
                            textStyle = TextStyle(
                                color = colorResource(id = R.color.black),
                                fontWeight = FontWeight.Normal
                            ),
                            shape = MaterialTheme.shapes.extraSmall,
                            placeholder = {
                                Text(
                                    text = "e.g. Google",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                                    textAlign = TextAlign.Start,
                                )
                            })

                        Spacer(modifier = Modifier.height(MediumPadding1))
                    }

                    if (textValue != "") {
                        items(filteredLocations.size) { index ->

                            val locationCard = filteredLocations[index]

                            LocationCard(locationCardModel = locationCard)

                            Spacer(modifier = Modifier.height(MediumPadding2))

                        }
                    }


                    item {
                        if (textValue == "") {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {

                                Icon(
                                    imageVector = Icons.Rounded.MyLocation,
                                    contentDescription = null,
                                    modifier = Modifier.scale(1f)
                                )

                                Spacer(modifier = Modifier.width(ExtraSmallPadding3))
                                Text(
                                    text = "Use current location",
                                    color = colorResource(id = R.color.black),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                    textAlign = TextAlign.Start,
                                )


                            }

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = MediumPadding2)
                                    .height(1.dp)
                                    .background(color = colorResource(id = R.color.lightGray))
                            )
                        }
                    }

                }

            }

        }


    }

}

@Composable
fun LocationCard(locationCardModel: LocationCardModel) {

    Row(
        modifier = Modifier.fillMaxWidth(),
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

            Text(
                text = locationCardModel.address.label,
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Clip,
                maxLines = 2
            )
        }

        Spacer(modifier = Modifier.width(ExtraSmallPadding1))


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