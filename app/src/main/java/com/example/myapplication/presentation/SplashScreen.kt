package com.example.myapplication.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {

    var showLogo by remember {
        mutableStateOf(false)
    }

    var showTitle by remember {
        mutableStateOf(false)
    }

    var showSubtitle by remember {
        mutableStateOf(false)
    }

    var finishAnimation by remember {
        mutableStateOf(false)
    }


    // -------------------------------------------------
    // Animation sequence
    // -------------------------------------------------

    LaunchedEffect(Unit) {

        delay(250)
        showLogo = true

        delay(550)
        showTitle = true

        delay(350)
        showSubtitle = true

        delay(1100)

        finishAnimation = true

        delay(300)

        onSplashFinished()
    }


    // -------------------------------------------------
    // Subtle background glow
    // -------------------------------------------------

    val infiniteTransition =
        rememberInfiniteTransition(
            label = "backgroundGlow"
        )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.10f,
        targetValue = 0.20f,

        animationSpec =
            infiniteRepeatable(
                animation =
                    tween(
                        durationMillis = 2200,
                        easing = FastOutSlowInEasing
                    ),

                repeatMode =
                    RepeatMode.Reverse
            ),

        label = "glowAlpha"
    )


    // -------------------------------------------------
    // Logo scale
    // -------------------------------------------------

    val logoScale by animateFloatAsState(

        targetValue =
            if (showLogo) {
                1f
            } else {
                0.72f
            },

        animationSpec =
            tween(
                durationMillis = 850,
                easing = FastOutSlowInEasing
            ),

        label = "logoScale"
    )


    // -------------------------------------------------
    // Screen
    // -------------------------------------------------

    Box(

        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(

                        colors =
                            listOf(
                                Color(0xFF171A20),
                                Color(0xFF0D0F13),
                                Color(0xFF07080A)
                            )
                    )
                ),

        contentAlignment =
            Alignment.Center
    ) {


        // -------------------------------------------------
        // Background glow
        // -------------------------------------------------

        Box(

            modifier =
                Modifier
                    .size(360.dp)
                    .alpha(glowAlpha)
                    .background(

                        Brush.radialGradient(

                            colors =
                                listOf(
                                    Color(0xFF6D5DFB),
                                    Color(0x226D5DFB),
                                    Color.Transparent
                                )
                        )
                    )
        )


        // -------------------------------------------------
        // Main content
        // -------------------------------------------------

        AnimatedVisibility(

            visible = !finishAnimation,

            exit =
                fadeOut(
                    animationSpec =
                        tween(300)
                )
        ) {

            Column(

                horizontalAlignment =
                    Alignment.CenterHorizontally,

                verticalArrangement =
                    Arrangement.Center
            ) {


                // -------------------------------------------------
                // Logo
                // -------------------------------------------------

                AnimatedVisibility(

                    visible = showLogo,

                    enter =
                        fadeIn(
                            animationSpec =
                                tween(700)
                        ) +
                                scaleIn(
                                    initialScale = 0.72f,

                                    animationSpec =
                                        tween(
                                            durationMillis = 850,
                                            easing =
                                                FastOutSlowInEasing
                                        )
                                )
                ) {

                    Box(

                        modifier =
                            Modifier
                                .size(120.dp)
                                .then(
                                    Modifier
                                        .alpha(
                                            if (showLogo) 1f else 0f
                                        )
                                ),

                        contentAlignment =
                            Alignment.Center
                    ) {

                        SamaangarLogo(
                            modifier =
                                Modifier
                                    .size(105.dp)
                                    .graphicsLayer {
                                        scaleX = logoScale
                                        scaleY = logoScale
                                    }
                        )
                    }
                }


                Spacer(
                    modifier =
                        Modifier.height(28.dp)
                )


                // -------------------------------------------------
                // App name
                // -------------------------------------------------

                AnimatedVisibility(

                    visible = showTitle,

                    enter =
                        fadeIn(
                            animationSpec =
                                tween(650)
                        ) +
                                slideInVertically(

                                    initialOffsetY = {
                                        it / 3
                                    },

                                    animationSpec =
                                        tween(
                                            durationMillis = 650,
                                            easing =
                                                FastOutSlowInEasing
                                        )
                                )
                ) {

                    Text(

                        text = "همانگار",

                        color =
                            Color.White,

                        fontSize =
                            32.sp,

                        fontWeight =
                            FontWeight.Bold,

                        letterSpacing =
                            0.4.sp,

                        textAlign =
                            TextAlign.Center
                    )
                }


                Spacer(
                    modifier =
                        Modifier.height(9.dp)
                )


                // -------------------------------------------------
                // Subtitle
                // -------------------------------------------------

                AnimatedVisibility(

                    visible = showSubtitle,

                    enter =
                        fadeIn(
                            animationSpec =
                                tween(700)
                        )
                ) {

                    Text(

                        text =
                            "مدیریت ساده، کسب‌وکار بهتر",

                        color =
                            Color.White.copy(
                                alpha = 0.48f
                            ),

                        fontSize =
                            12.sp,

                        fontWeight =
                            FontWeight.Normal,

                        letterSpacing =
                            0.2.sp,

                        textAlign =
                            TextAlign.Center
                    )
                }
            }
        }
    }
}


/* =====================================================
 * LOGO
 * ===================================================== */

@Composable
private fun SamaangarLogo(
    modifier: Modifier = Modifier
) {

    Canvas(
        modifier = modifier
    ) {

        val w = size.width
        val h = size.height


        val stroke =
            w * 0.075f


        // -------------------------------------------------
        // Outer rounded shape
        // -------------------------------------------------

        drawRoundRect(

            brush =
                Brush.linearGradient(

                    colors =
                        listOf(
                            Color(0xFFD5CCFF),
                            Color(0xFF8A78FF),
                            Color(0xFF6654E8)
                        )
                ),

            topLeft =
                Offset(
                    w * 0.18f,
                    h * 0.18f
                ),

            size =
                Size(
                    w * 0.64f,
                    h * 0.64f
                ),

            cornerRadius =
                CornerRadius(
                    w * 0.18f,
                    w * 0.18f
                ),

            style =
                Stroke(
                    width = stroke
                )
        )


        // -------------------------------------------------
        // Inner connection
        // -------------------------------------------------

        val pathStroke =
            Stroke(
                width = stroke,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )


        drawLine(

            brush =
                Brush.linearGradient(

                    colors =
                        listOf(
                            Color.White,
                            Color(0xFFA99BFF)
                        )
                ),

            start =
                Offset(
                    w * 0.32f,
                    h * 0.49f
                ),

            end =
                Offset(
                    w * 0.46f,
                    h * 0.62f
                ),

            strokeWidth =
                stroke,

            cap =
                StrokeCap.Round
        )


        drawLine(

            brush =
                Brush.linearGradient(

                    colors =
                        listOf(
                            Color(0xFFA99BFF),
                            Color.White
                        )
                ),

            start =
                Offset(
                    w * 0.46f,
                    h * 0.62f
                ),

            end =
                Offset(
                    w * 0.69f,
                    h * 0.36f
                ),

            strokeWidth =
                stroke,

            cap =
                StrokeCap.Round
        )


        // -------------------------------------------------
        // Small brand point
        // -------------------------------------------------

        drawCircle(

            brush =
                Brush.radialGradient(

                    colors =
                        listOf(
                            Color.White,
                            Color(0xFF9D8EFF)
                        )
                ),

            radius =
                w * 0.045f,

            center =
                Offset(
                    w * 0.72f,
                    h * 0.27f
                )
        )
    }
}


