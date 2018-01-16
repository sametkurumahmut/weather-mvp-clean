package io.sametkurumahmut.weather.remote.service.response

object WeatherAPI {

    const val API_KEY = "f53d4280cf9c45521e1eacbe7620acb5" // OpenWeatherMap API KEY
    const val BASE_URL = "https://api.openweathermap.org" // OpenWeatherMap Base URL
    const val IMG_URL = "https://openweathermap.org/img/w/" // OpenWeatherMap Image URL

    const val IMG_ICON_FORMAT = "${IMG_URL}%s.png" // OpenWeatherMap Image URL icon format

    const val FIELD_APPID = "appid" // appid
    const val FIELD_APPID_FORMAT = "${FIELD_APPID}=" // appid=
    const val FIELD_APPID_ADD = "&${FIELD_APPID_FORMAT}" // &appid=
    const val FIELD_APPID_ALL = "${FIELD_APPID_FORMAT}${API_KEY}" // appid=API_KEY
    const val FIELD_APPID_ALL_ADD = "${FIELD_APPID_ADD}${API_KEY}" // &appid=API_KEY

    const val FIELD_LAT = "lat" // lat
    const val FIELD_LAT_FORMAT = "${FIELD_LAT}=" // lat=
    const val FIELD_LAT_ADD = "&${FIELD_LAT_FORMAT}" // &lat=
    const val FIELD_LON = "lon" // lon
    const val FIELD_LON_FORMAT = "${FIELD_LON}=" // lon=
    const val FIELD_LON_ADD = "&${FIELD_LON_FORMAT}" // &lon=

    const val FIELD_UNITS = "units" // units
    const val FIELD_UNITS_FORMAT = "${FIELD_UNITS}=" // units=
    const val FIELD_UNITS_ADD = "&${FIELD_UNITS_FORMAT}" // &units=

    const val REQUEST = "/data/2.5/" // /data/2.5/

    const val REQUEST_DAILY_FORECAST_SHORT = "forecast/daily" // forecast/daily
    const val REQUEST_DAILY_FORECAST_SHORT_ADD = "forecast/daily?" // forecast/daily?
    const val REQUEST_DAILY_FORECAST = "${REQUEST}${REQUEST_DAILY_FORECAST_SHORT_ADD}${FIELD_APPID_ALL}" // /data/2.5/forecast/daily?appid=API_KEY
    const val REQUEST_DAILY_FORECAST_WO_ADD = "${REQUEST}${REQUEST_DAILY_FORECAST_SHORT}" // /data/2.5/forecast/daily
    const val REQUEST_DAILY_FORECAST_ADD_WO_API_KEY = "${REQUEST}${REQUEST_DAILY_FORECAST_SHORT_ADD}" // /data/2.5/forecast/daily? (WITHOUT API KEY)

    const val REQUEST_FORECAST_SHORT = "forecast" // forecast
    const val REQUEST_FORECAST_SHORT_ADD = "forecast?" // forecast?
    const val REQUEST_FORECAST = "${REQUEST}${REQUEST_FORECAST_SHORT_ADD}${FIELD_APPID_ALL}" // /data/2.5/forecast?appid=API_KEY
    const val REQUEST_FORECAST_WO_ADD = "${REQUEST}${REQUEST_FORECAST_SHORT}" // /data/2.5/forecast
    const val REQUEST_FORECAST_ADD_WO_API_KEY = "${REQUEST}${REQUEST_FORECAST_SHORT_ADD}" // /data/2.5/forecast? (WITHOUT API KEY)

    const val REQUEST_WEATHER_SHORT = "weather" // weather
    const val REQUEST_WEATHER_SHORT_ADD = "weather?" // weather?
    const val REQUEST_WEATHER = "${REQUEST}${REQUEST_WEATHER_SHORT_ADD}${FIELD_APPID_ALL}" // /data/2.5/weather?appid=API_KEY
    const val REQUEST_WEATHER_WO_ADD = "${REQUEST}${REQUEST_WEATHER_SHORT}" // /data/2.5/weather
    const val REQUEST_WEATHER_ADD_WO_API_KEY = "${REQUEST}${REQUEST_WEATHER_SHORT_ADD}" // /data/2.5/weather? (WITHOUT API KEY)
}
