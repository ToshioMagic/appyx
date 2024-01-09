package com.bumble.appyx.navigation.navigation

import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node

fun interface ChildNodeBuilder<ChildReference> {

    fun buildChildNode(reference: ChildReference, buildContext: BuildContext): Node
}
