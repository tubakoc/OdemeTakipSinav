package com.example.trackingpayment.MODELS

import kotlin.properties.Delegates

class OdemeTip {
    var Id by Delegates.notNull<Int>()
    var Baslik : String? = null
    var OdemePeriyod : String? = null
    var PeriyodGun : Int? = null

}