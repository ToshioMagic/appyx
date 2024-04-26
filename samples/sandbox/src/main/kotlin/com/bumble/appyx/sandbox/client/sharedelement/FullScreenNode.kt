package com.bumble.appyx.sandbox.client.sharedelement

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.navigation.transition.sharedElement
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.samples.common.profile.Profile
import com.bumble.appyx.samples.common.profile.ProfileImage

class FullScreenNode(
    private val onClick: (Int) -> Unit,
    private val profileId: Int,
    buildContext: BuildContext
) : Node(buildContext) {

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    override fun View(modifier: Modifier) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .clickable {
                    onClick(profileId)
                }
        ) {
            val profile = Profile.allProfiles[profileId]
            ProfileImage(
                profile.drawableRes, modifier = Modifier
                    .fillMaxSize()
                    .sharedElement(key = "$profileId image")
            )

            Text(
                text = "${profile.name}, ${profile.age}",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .sharedElement(key = "$profileId text")
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}
