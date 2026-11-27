package hunter.entry.stage3

import hunter.app.installv3.installV3
import hunter.app.installv4.installV4
import hunter.app.installv5.installV5
import hunter.app.installv6.installV6
import hunter.app.installv7.installV7
import hunter.app.installvectorplayer.installVectorPlayer
import hunter.app.installv8.installV8
import hunter.app.installv9.installV9
import hunter.app.installv93.installV93
import hunter.app.installv10.installV10
import hunter.app.installv11.installV11

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["v3.js"] = ::installV3
    registry["v4.js"] = ::installV4
    registry["v5.js"] = ::installV5
    registry["v6.js"] = ::installV6
    registry["v7.js"] = ::installV7
    registry["vector-player.js"] = ::installVectorPlayer
    registry["v8.js"] = ::installV8
    registry["v9.js"] = ::installV9
    registry["v93.js"] = ::installV93
    registry["v10.js"] = ::installV10
    registry["v11.js"] = ::installV11
}
