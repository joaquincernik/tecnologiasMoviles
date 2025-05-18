package com.undef.manosLocalesCernikGaribaldi.activities.components

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.undef.manosLocalesCernikGaribaldi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController, volverActivity: Boolean = false, arrowInvisible: Boolean = false) {

    //como hayq ue volver a otra avtivity se hace esto
    val context = LocalContext.current
    val activity = context as? Activity

    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logoletras),
                contentDescription = "Logo",
                modifier = Modifier.size(180.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        navigationIcon = {
            //si le llega arrowInvisible va a esconder la arrow
            if(!arrowInvisible){
            IconButton(onClick = {
                //aca tengo que decidir si vuelvo a otra activity o si vuelvo a lapila de nav
                if (volverActivity) {
                    activity?.finish()
                }
                else{
                    navController.popBackStack()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Volver",

                    )
            }}
        },


        modifier = Modifier
            .background(color = Color.White)
            .padding(14.dp)
    )
}
