package com.bumble.appyx.sandbox.client.spotlightadvancedexample

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.spotlightadvanced.SpotlightAdvanced
import com.bumble.appyx.navmodel.spotlightadvanced.backpresshandler.GoToPrevious
import com.bumble.appyx.navmodel.spotlightadvanced.isCarousel
import com.bumble.appyx.navmodel.spotlightadvanced.operation.next
import com.bumble.appyx.navmodel.spotlightadvanced.operation.previous
import com.bumble.appyx.navmodel.spotlightadvanced.operation.switchToCarousel
import com.bumble.appyx.navmodel.spotlightadvanced.operation.switchToPager
import com.bumble.appyx.navmodel.spotlightadvanced.transitionhandler.rememberSpotlightAdvancedSlider
import com.bumble.appyx.sandbox.client.profilecard.ProfileCardNode
import kotlinx.parcelize.Parcelize

@Suppress("MaxLineLength")
class SpotlightAdvancedExampleNode(
    buildContext: BuildContext,
    private val spotlightAdvanced: SpotlightAdvanced<NavTarget> = SpotlightAdvanced(
        items = listOf(
            NavTarget.Michaelle,
            NavTarget.Lara,
            NavTarget.Victoria,
            NavTarget.Jessica,
            NavTarget.Kane
        ),
        savedStateMap = buildContext.savedStateMap,
        backPressHandler = GoToPrevious(),
    )
) : ParentNode<SpotlightAdvancedExampleNode.NavTarget>(
    buildContext = buildContext,
    navModel = spotlightAdvanced
) {

    @Parcelize
    sealed class NavTarget(val url: String, val name: String, val age: Int) : Parcelable {

        @Parcelize
        object Michaelle : NavTarget(
            url = "https://images.pexels.com/photos/1572878/pexels-photo-1572878.jpeg?auto=compress&cs=tinysrgb&w=1600",
            name = "Michaelle",
            age = 21
        )

        @Parcelize
        object Lara : NavTarget(
            url = "https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            name = "Lara",
            age = 19
        )

        @Parcelize
        object Victoria : NavTarget(
            url = "https://images.pexels.com/photos/2235071/pexels-photo-2235071.jpeg?auto=compress&cs=tinysrgb&w=1600",
            name = "Victoria",
            age = 25
        )

        @Parcelize
        object Jessica : NavTarget(
            url = "https://images.pexels.com/photos/2811089/pexels-photo-2811089.jpeg?auto=compress&cs=tinysrgb&w=1600",
            name = "Jessica",
            age = 24
        )

        @Parcelize
        object Kane : NavTarget(
            url = "https://images.pexels.com/photos/428340/pexels-photo-428340.jpeg?auto=compress&cs=tinysrgb&w=1600",
            name = "Kane",
            age = 27
        )
    }

    override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node =
        ProfileCardNode(
            imageUrl = navTarget.url,
            name = navTarget.name,
            age = navTarget.age,
            buildContext = buildContext
        )

    @Composable
    override fun View(modifier: Modifier) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Children(
                modifier = Modifier.fillMaxWidth(),
                transitionHandler = rememberSpotlightAdvancedSlider(),
                navModel = spotlightAdvanced
            )
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val isCarousel by spotlightAdvanced.isCarousel().collectAsState(initial = false)
                TextButton(
                    onClick = {
                        if (isCarousel) {
                            spotlightAdvanced.switchToPager()
                        } else {
                            spotlightAdvanced.switchToCarousel()
                        }
                    }
                ) {
                    Text(
                        text = (if (isCarousel) "Pager" else "Carousel").toUpperCase(Locale.current),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                TextButton(
                    onClick = { spotlightAdvanced.previous() }
                ) {
                    Text(
                        color = Color.White,
                        text = "Previous".toUpperCase(Locale.current),
                        fontWeight = FontWeight.Bold
                    )
                }
                TextButton(
                    onClick = { spotlightAdvanced.next() }
                ) {
                    Text(
                        color = Color.White,
                        text = "Next".toUpperCase(Locale.current),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
