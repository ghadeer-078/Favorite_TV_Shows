package com.example.ghadeer_s.Model


data class Show(
    var image: Image? = Image(),
    val language: String? = "", // English
    val name: String = "", // Bomb Girls
    val summary: String? = "", // <p>Set in the 1940s, <b>Bomb Girls</b> tells the remarkable stories of the women who risked their lives in a munitions factory building bombs for the Allied forces fighting on the European front. The series delves into the lives of these exceptional women – peers, friends and rivals – who find themselves thrust into new worlds and changed profoundly as they are liberated from their home and social restrictions.</p>
)
