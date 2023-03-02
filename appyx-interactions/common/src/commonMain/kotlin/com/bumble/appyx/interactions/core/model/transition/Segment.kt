package com.bumble.appyx.interactions.core.model.transition

data class Segment<ModelState>(
    val stateTransition: StateTransition<ModelState>,
) {

    val fromState: ModelState
        get() = stateTransition.fromState

    val targetState: ModelState
        get() = stateTransition.targetState

    fun replace(targetState: ModelState): Segment<ModelState> =
        copy(
            stateTransition = stateTransition.copy(
                targetState = targetState
            )
        )
}
