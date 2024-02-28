import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.presentation.homeScreen.slidingAds.SlidingAdBanner


@Composable
fun SlidingAdBannerView(modifier: Modifier = Modifier, slidingAdBanner: SlidingAdBanner) {

    Card(
        modifier = Modifier
            .height(140.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(colorResource(id = slidingAdBanner.background)),
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .width(120.dp)
                ) {
                    Text(
                        text = slidingAdBanner.title,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
                        color = colorResource(
                            id = slidingAdBanner.buttonColor
                        ),
                        overflow = TextOverflow.Visible
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = slidingAdBanner.buttonColor),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .size(100.dp), contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Order now",
                        style = MaterialTheme.typography.titleSmall.copy(color = Color.White)
                    )

                }
            }

            Image(
                painter = painterResource(id = slidingAdBanner.imageId),
                contentDescription = null,
                modifier = Modifier
                    .scale(1f)
                    .align(Alignment.BottomEnd)

            )
        }

    }

}
