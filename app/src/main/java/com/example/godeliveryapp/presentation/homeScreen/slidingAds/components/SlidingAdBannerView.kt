import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.godeliveryapp.presentation.Dimens.ExtraSmallPadding2
import com.example.godeliveryapp.presentation.Dimens.MediumPadding2
import com.example.godeliveryapp.presentation.Dimens.NormalPadding
import com.example.godeliveryapp.presentation.homeScreen.slidingAds.SlidingAdBanner


@Composable
fun SlidingAdBannerView(modifier: Modifier = Modifier, slidingAdBanner: SlidingAdBanner) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier
            .background(
                color = colorResource(id = slidingAdBanner.background),
                shape = RoundedCornerShape(12.dp)
            )
            .height(screenHeight / 5)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(NormalPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = slidingAdBanner.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = colorResource(
                        id = slidingAdBanner.buttonColor
                    ),
                )

                if (slidingAdBanner.description != null) {
                    Text(
                        text = slidingAdBanner.description,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal),
                        color = colorResource(
                            id = slidingAdBanner.buttonColor
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(MediumPadding2))

                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = slidingAdBanner.buttonColor),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(ExtraSmallPadding2),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = slidingAdBanner.buttonTitle,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.white)
                    )

                }
            }

            Image(
                painter = painterResource(id = R.drawable.giftcard),
                contentDescription = null,
                modifier = Modifier.size(screenHeight / 6),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
        }
    }


}
