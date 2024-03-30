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
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import com.example.godeliveryapp.data.remote.dataTransferObject.CartOrderItemDto
import com.example.godeliveryapp.presentation.CartScreen.CartScreenViewModel
import com.example.godeliveryapp.presentation.Dimens
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding3
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.detailsScreen.menuItems.MenuItemCardModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItemCardView(
    modifier: Modifier = Modifier,
    menuItemCardModel: MenuItemCardModel,
    viewModel: CartScreenViewModel = hiltViewModel()
) {
    val cartItems = viewModel.cartItems.collectAsState(initial = listOf()).value
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

    val existingItem =
        cartItems?.firstOrNull { it.itemId == menuItemCardModel.itemId }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
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
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = menuItemCardModel.itemName,
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
                        text = menuItemCardModel.itemDescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Text(
                    text = "₹ ${menuItemCardModel.itemPrice}",
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
                        .height(120.dp)
                        .width(118.dp)
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

                                            val cartOrderItemDto = CartOrderItemDto(
                                                cartId = 1,
                                                itemId = menuItemCardModel.itemId,
                                                quantity = existingItem.quantity
                                            )

                                            if (existingItem.quantity > 1) {
                                                viewModel.updateCartItem(
                                                    cartOrderItemDto.copy(
                                                        quantity = cartOrderItemDto.quantity - 1
                                                    )
                                                )
                                            } else {
                                                viewModel.deleteCartItem(menuItemCardModel.itemId)
                                            }

                                        },
                                    tint = colorResource(
                                        id = R.color.black
                                    )
                                )
                                Text(
                                    text = "${existingItem.quantity}",
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                                )
                                Icon(
                                    imageVector = Icons.Rounded.Add,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .scale(0.8f)
                                        .clickable {

                                            val cartOrderItemDto = CartOrderItemDto(
                                                cartId = 1,
                                                itemId = menuItemCardModel.itemId,
                                                quantity = existingItem.quantity
                                            )

                                            viewModel.updateCartItem(
                                                cartOrderItemDto.copy(
                                                    quantity = cartOrderItemDto.quantity + 1
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
                        .height((screenHeight).dp)
                ) {
                    Column {
                        //HeroSection
                        Box(
                            modifier = Modifier
                                .height((screenHeight / 3).dp)
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
                                verticalArrangement = Arrangement.Center
                            ) {
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = menuItemCardModel.itemName,
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
                                    text = "₹ ${menuItemCardModel.itemPrice}",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = colorResource(id = R.color.black),
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = menuItemCardModel.itemDescription,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray,
                                    overflow = TextOverflow.Visible,
                                )

                                Spacer(modifier = Modifier.height(Dimens.MediumPadding2))

                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(color = colorResource(id = R.color.lightGray))
                                )
                            }

                            val paddingValues =
                                WindowInsets.navigationBars.asPaddingValues()


                            OutlinedButton(
                                onClick = {

                                    val cartOrderItemDto = CartOrderItemDto(
                                        cartId = 1,
                                        itemId = menuItemCardModel.itemId,
                                        quantity = 1
                                    )

                                    viewModel.createItem(
                                        cartOrderItemDto
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
                                    "Add to cart - ₹ ${menuItemCardModel.itemPrice}",
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