package com.example.covbin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConverterScreen()
        }
    }
}


@Composable
fun ConverterScreen() {
    var number by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        InputField(
            value = number,
            onValueChange = { number = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ConvertButton(
            onClick = {
                result = convertToBinary(number)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ResultDisplay(result = result)
    }
}


@Composable
fun InputField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) }, // utilisation de "it"
        label = { Text("Entrer un nombre") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ConvertButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Convertir")
    }
}

@Composable
fun ResultDisplay(result: String) {
    Text(
        text = "Résultat : $result",
        style = MaterialTheme.typography.headlineSmall
    )
}


fun convertToBinary(input: String): String {
    return try {
        Integer.toBinaryString(input.toInt())
    } catch (e: Exception) {
        "Entrée invalide"
    }
}