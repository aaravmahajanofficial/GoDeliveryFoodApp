package com.example.godeliveryapp.presentation.detailsScreen.menuItems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.godeliveryapp.R
import com.example.godeliveryapp.domain.model.CartItemModel
import com.example.godeliveryapp.presentation.CartScreen.CartScreenViewModel
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.MediumPadding1
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.common.CustomLineBreak
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItemCardView(
    modifier: Modifier = Modifier,
    menuItemModel: MenuItemModel,
    cartViewModel: CartScreenViewModel = hiltViewModel(),
) {
    val cartItems = cartViewModel.cartItems.collectAsState(initial = emptyList()).value
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    val yesCheckBoxState = remember { mutableStateOf(false) }
    val noCheckBoxState = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    fun onClick() {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = true
            }
        }
    }

    var existingItem = cartItems.firstOrNull { it.menuItemModel.itemId == menuItemModel.itemId }

    LaunchedEffect(cartItems) {

        existingItem = cartItems.firstOrNull { it.menuItemModel.itemId == menuItemModel.itemId }

    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = NormalPadding, end = NormalPadding, top = NormalPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = menuItemModel.itemName,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(
                            id = R.color.black
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Visible,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = menuItemModel.itemDescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Text(
                    text = "₹ ${menuItemModel.itemPrice}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = colorResource(id = R.color.black),
                )

                Spacer(modifier = Modifier.height(NormalPadding))

            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                Box(
                    modifier = Modifier
                        .height(110.dp)
                        .width(108.dp)
                        .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                        .clip(
                            RoundedCornerShape(12.dp)
                        ), contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.restaurant2),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    if (existingItem != null) {
                        Box(
                            modifier = Modifier
                                .padding(bottom = ExtraSmallPadding3)
                                .align(Alignment.BottomCenter)
                                .background(
                                    color = colorResource(id = R.color.lightGray),
                                    shape = RoundedCornerShape(18.dp)
                                )
                                .height(35.dp)
                                .width(80.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Remove,
                                    contentDescription = null, modifier = Modifier
                                        .scale(0.8f)
                                        .clickable {

                                            if (existingItem!!.quantity > 1) {
                                                cartViewModel.upsertCartItem(
                                                    cartItem = CartItemModel(
                                                        restaurantId = menuItemModel.restaurantId,
                                                        menuItemModel = menuItemModel,
                                                        quantity = existingItem!!.quantity - 1,
                                                    )
                                                )
                                            } else {
                                                cartViewModel.deleteCartItem(
                                                    cartItem = CartItemModel(
                                                        restaurantId = menuItemModel.restaurantId,
                                                        menuItemModel = menuItemModel,
                                                        quantity = 1
                                                    )
                                                )
                                            }

                                        },
                                    tint = colorResource(
                                        id = R.color.black
                                    )
                                )
                                Text(
                                    text = "${existingItem!!.quantity}",
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                                )
                                Icon(
                                    imageVector = Icons.Rounded.Add,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .scale(0.8f)
                                        .clickable {

                                            cartViewModel.upsertCartItem(
                                                cartItem = CartItemModel(
                                                    restaurantId = menuItemModel.restaurantId,
                                                    menuItemModel = menuItemModel,
                                                    quantity = existingItem!!.quantity + 1,
                                                )
                                            )

                                        },
                                    tint = colorResource(
                                        id = R.color.black
                                    )
                                )

                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(ExtraSmallPadding3)
                                .background(color = Color.White, shape = CircleShape)
                                .size(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = null,
                                tint = colorResource(id = R.color.black),
                                modifier = Modifier
                                    .scale(1f)
                                    .clickable { onClick() }
                            )
                        }
                    }


                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Customisable",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                    color = colorResource(
                        id = R.color.gray
                    )
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
                        .height((screenHeight))
                ) {
                    Column {
                        //HeroSection
                        Box(
                            modifier = Modifier
                                .height((screenHeight / 3))
                                .fillMaxWidth()
                                .background(
                                    Color.Transparent,
                                    shape = RoundedCornerShape(12.dp)
                                )
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.restaurant2),
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                            Box(modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(NormalPadding)
                                .clickable {
                                    showBottomSheet = false
                                }
                                .background(
                                    color = Color.White,
                                    shape = CircleShape
                                )
                                .size(42.dp),
                                contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.black),
                                    modifier = Modifier.scale(1f)
                                )
                            }

                        }
                        //Below Image
                        Column(
                            modifier
                                .fillMaxSize()
                                .padding(start = NormalPadding, end = NormalPadding),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = menuItemModel.itemName,
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = colorResource(
                                        id = R.color.black
                                    ),
                                    overflow = TextOverflow.Visible,
                                    maxLines = 2,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "₹ ${menuItemModel.itemPrice}",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = colorResource(id = R.color.black),
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = menuItemModel.itemDescription,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray,
                                    overflow = TextOverflow.Visible,
                                )

                                CustomLineBreak()

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Need Cutlery",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold
                                        ),
                                        color = colorResource(id = R.color.black),
                                    )

                                    Row(
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Icon(
                                            modifier = Modifier.size(screenHeight.div(38)),
                                            imageVector = Icons.Rounded.Check,
                                            contentDescription = null,
                                            tint = colorResource(id = R.color.secondaryColor)
                                        )
                                        Text(
                                            text = "Required",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.Normal
                                            ),
                                            color = colorResource(
                                                id = R.color.secondaryColor
                                            ),
                                            maxLines = 1,
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(MediumPadding1))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        text = "Yes",
                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                                        color = colorResource(id = R.color.black)
                                    )

                                    Box(
                                        modifier = Modifier.size(screenHeight / 52),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Checkbox(
                                            modifier = Modifier.scale(0.8f),
                                            enabled = !noCheckBoxState.value,
                                            checked = yesCheckBoxState.value,
                                            onCheckedChange = { yesCheckBoxState.value = it },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = colorResource(id = R.color.black),
                                                uncheckedColor = colorResource(id = R.color.gray),
                                                checkmarkColor = colorResource(id = R.color.white)
                                            )
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(NormalPadding))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        text = "No, thank you!!",
                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                                        color = colorResource(id = R.color.black)
                                    )

                                    Box(modifier = Modifier.size(screenHeight / 52)) {
                                        Checkbox(
                                            modifier = Modifier.scale(0.8f),
                                            enabled = !yesCheckBoxState.value,
                                            checked = noCheckBoxState.value,
                                            onCheckedChange = { noCheckBoxState.value = it },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = colorResource(id = R.color.black),
                                                uncheckedColor = colorResource(id = R.color.gray),
                                                checkmarkColor = colorResource(id = R.color.white)
                                            )
                                        )
                                    }
                                }
                            }

                            val paddingValues =
                                WindowInsets.navigationBars.asPaddingValues()


                            OutlinedButton(
                                onClick = {

                                    cartViewModel.upsertCartItem(
                                        cartItem = CartItemModel(
                                            menuItemModel = menuItemModel,
                                            restaurantId = menuItemModel.restaurantId,
                                            quantity = if (existingItem != null) existingItem!!.quantity + 1 else 1
                                        )
                                    )

                                    showBottomSheet = false

                                },
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(
                                        id = R.color.black
                                    ),
                                ),
                                border = BorderStroke(0.dp, Color.Transparent),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = paddingValues.calculateBottomPadding() * 2)
                                    .height(50.dp)
                            ) {

                                Text(
                                    "Add to cart - ₹ ${menuItemModel.itemPrice}",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Medium
                                    )
                                )

                            }


                        }

                    }


                }

            }
        }

    }

}