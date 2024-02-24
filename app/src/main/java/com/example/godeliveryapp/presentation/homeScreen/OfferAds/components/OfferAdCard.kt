import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.godeliveryapp.R
import com.example.zomatoclone.presentation.Dimens
import com.example.zomatoclone.presentation.Dimens.MediumPadding2
import com.example.zomatoclone.presentation.homeScreen.OfferAds.OfferAd


@Composable
fun OfferAdCard(modifier: Modifier = Modifier, offerAd: OfferAd) {

    Card(
        modifier = Modifier
            .height(220.dp)
            .padding(start = MediumPadding2),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(colorResource(id = offerAd.background)),
    ) {

        Row(
            modifier = Modifier,
        ) {
            Column(
                modifier = Modifier
                    .padding(Dimens.MediumPadding1),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = offerAd.title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
                    color = colorResource(
                        id = R.color.black
                    )
                )
                Spacer(modifier = Modifier.height(100.dp))
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = offerAd.buttonColor))
                ) {

                    Text(text = "Order now", style = MaterialTheme.typography.titleMedium)

                }
            }

            Image(
                painter = painterResource(id = offerAd.imageId),
                contentDescription = null,

                modifier = Modifier
                    .fillMaxSize()
                    .scale(0.8f)
                    .offset(x = 40.dp, y = (-10).dp)

            )
        }

    }

}
