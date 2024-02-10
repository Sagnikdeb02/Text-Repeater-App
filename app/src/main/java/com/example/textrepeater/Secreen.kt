package com.example.textrepeater

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import com.example.textrepeater.ui.theme.Output

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen() {
    var inputText by remember {
        mutableStateOf("")
    }
    var outputText by remember {
        mutableStateOf("")
    }
    var count by remember {
        mutableStateOf(1)
    }
    var isChecked by remember {
        mutableStateOf(false)
    }
    var isCopied by remember {
        mutableStateOf(false)
    }
    var newLine by remember {
        mutableStateOf(false)
    }
    var period by remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box() {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column() {
            Row(
                modifier = Modifier
                    .background(colorResource(id = R.color.purple_500))
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp, 50.dp),
                    contentScale = ContentScale.Fit
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(
                            text = "Text Repeater",
                            fontSize = 40.sp,
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(
                            text = "for WhatsApp, Messenger, SMS, Telegram and More",
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.padding(16.dp))
                        Box(
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(20.dp))
                                .fillMaxWidth()
                                .size(350.dp),
                        )
                        {
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxSize(),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Text to repeat :",
                                        fontSize = 26.sp,
                                        textAlign = TextAlign.Left,
                                        color = Color.Black,
                                        fontFamily = FontFamily.Cursive,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.padding(horizontal = 70.dp))
                                    Button(
                                        onClick = { inputText = ""; outputText = "";count = 1
                                                  keyboardController?.hide()},
                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                        border = BorderStroke(2.dp, Color.Blue)
                                    ) {
                                        Text(
                                            text = "Clear",
                                            color = Color.Black
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.padding(8.dp))
                                TextField(
                                    value = inputText,
                                    onValueChange = {
                                        inputText = it
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            keyboardController?.hide()
                                        }
                                    ),
                                    label = {
                                        if (inputText == "" || inputText.isEmpty())
                                            Text(text = "Enter Text :", color = Color.Black)
                                    },
                                    colors = TextFieldDefaults.textFieldColors(
                                        textColor = Color.Black,
                                        cursorColor = Color.Black,
                                        backgroundColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(20.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .clip(RoundedCornerShape(20.dp))
                                        .border(1.dp, Color.Black, RoundedCornerShape(20.dp)),
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                    ) {
                                        Checkbox(
                                            checked = newLine, onCheckedChange = {
                                                newLine = it
                                            },
                                            modifier = Modifier
                                                .size(20.dp),
                                            colors = CheckboxDefaults.colors(
                                                uncheckedColor = Color.Black
                                            )
                                        )
                                        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                                        Text(text = "Add Newline", color = Color.Black)
                                    }
                                    Spacer(modifier = Modifier.padding(40.dp))
                                    Button(onClick = {
                                        outputText =
                                            Output(inputText, newLine, period, count, isChecked)
                                        isCopied = false
                                        keyboardController?.hide()
                                    }
                                    ) {
                                        Text(
                                            text = "Repeat Text",
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier.height(50.dp)
                                ) {
                                    Checkbox(
                                        checked = period, onCheckedChange = {
                                            period = it
                                        },
                                        modifier = Modifier
                                            .size(20.dp),
                                        colors = CheckboxDefaults.colors(uncheckedColor = Color.Black)
                                    )
                                    Spacer(modifier = Modifier.padding(10.dp))
                                    Text(text = "Add Period", color = Color.Black)
                                }
                                Row {
                                    Checkbox(
                                        checked = isChecked, onCheckedChange = {
                                            isChecked = it
                                        },
                                        modifier = Modifier
                                            .size(20.dp),
                                        colors = CheckboxDefaults.colors(uncheckedColor = Color.Black)
                                    )
                                    Spacer(modifier = Modifier.padding(10.dp))
                                    Text(
                                        text = "Add Numbering",
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.padding(60.dp))
                                    TextField(
                                        value = count.toString(),
                                        onValueChange = {
                                            try {
                                                count = it.toInt()
                                            } catch (e: NumberFormatException) {
                                                count = 0
                                            }
                                        },
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Decimal,
                                        ),
                                        label = {
                                                if(count == 1){
                                                    Text(text = "Amount :", color = Color.Black)
                                                }
                                        },
                                        colors = TextFieldDefaults.textFieldColors(
                                            textColor = Color.Black,
                                            cursorColor = Color.Black,
                                            backgroundColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(20.dp),
                                        modifier = Modifier
                                            .width(100.dp)
                                            .height(50.dp)
                                            .clip(RoundedCornerShape(5.dp))
                                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Box(
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(20.dp))
                                .fillMaxWidth()
                                .size(300.dp)
                        )
                        {
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxSize(),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Output :",
                                        fontSize = 26.sp,
                                        textAlign = TextAlign.Left,
                                        color = Color.Black,
                                        fontFamily = FontFamily.Cursive,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.padding(horizontal = 70.dp))
                                    Button(
                                        onClick = { /*TODO*/ },
                                        border = BorderStroke(2.dp, Color.Blue),
                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                                    ) {
                                        Text(
                                            text = "Select Output",
                                            color = Color.Black
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.padding(8.dp))
                                OutlinedTextField(
                                    value = outputText,
                                    onValueChange = {},
                                    readOnly = true,
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = Color.White,
                                        textColor = Color.Black,
                                        cursorColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(20.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .align(Alignment.CenterHorizontally)
                                        .clip(RoundedCornerShape(20.dp))
                                        .border(1.dp, Color.Black, RoundedCornerShape(20.dp)),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Box(
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(20.dp))
                                .fillMaxWidth()
                                .padding(8.dp)
                                .size(300.dp)
                        ) {
                            Column() {
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(
                                    text = "Welcome to TextRepeater App",
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.padding(5.dp))
                                Text(
                                    text = "\uD83D\uDD01  Repeat text and emojis multiple times.\n\n" +
                                            "✅  No account and no hassle to use - it's easy and free!\n\n" +
                                            "Text Repeater is your go-to app for all things repetition. " +
                                            "Our process is simple, just add text, repeat, copy, and share! " +
                                            "Whether you want to entertain your friends with endless emojis or drop a " +
                                            "full-fledged text bomb, our easy-to-use app is here for all your needs. " +
                                            "Plus, you get a built-in word and character counter in your input box.",
                                    color = Color.Black
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Box(
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(20.dp))
                                .fillMaxWidth()
                                .padding(8.dp)
                                .size(350.dp)
                        ) {
                            Column {
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(
                                    text = "How Does TextRepeater Work?",
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.padding(5.dp))
                                Text(
                                    text = "Our simple app allows you to endlessly replicate " +
                                            "whatever text, emoji, or punctuation you want!",
                                    color = Color.Black
                                )
                                Text(
                                    text = "1. Enter your text into the message box and choose your settings.",
                                    color = Color.Black
                                )
                                Text(
                                    text = "2. Type in how many times you would like your it repeated.",
                                    color = Color.Black
                                )
                                Text(
                                    text = "3. If you want to add order to your repetition to have some" +
                                            "order, you can add a space or period in between your text.",
                                    color = Color.Black
                                )
                                Text(
                                    text = "4. If you prefer to start your content on a new line, just tap the Add Line option.",
                                    color = Color.Black
                                )
                                Text(
                                    text = "5. Once ready, you simply hit \"Repeat Text\" to repeat your text.",
                                    color = Color.Black
                                )
                            }
                        }
                    }

                }
            }
        }

    }
}




@Preview(showSystemUi = true)
@Composable
fun View() {
    MainScreen()
}
