import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {

    Card(
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.NormalPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(180.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Lazeez Bhina Murgh Chicken Dum Pizza",
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
                    text = "Boneless, Served with 1 Gulab Jamun & Mint Raita",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

                Text(
                    text = "₹ 365",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    color = colorResource(id = R.color.black),
                )


            }

            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxHeight()
                    .padding(bottom = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
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

                    //            restaurantListingCard.imageId?.let { painterResource(id = it) }?.let {

                    Image(
                        painter = painterResource(id = R.drawable.restaurant2),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    //            }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp)
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
                                .clickable { onClick?.invoke() }
                        )
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
        }

    }


}