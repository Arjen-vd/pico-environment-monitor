# This code runs on the Raspberry Pi Pico 2 W
# Please insert your own wifi and API config

# Imports
import network
import urequests
import ujson

from machine import Pin
import dht
from time import sleep


# Config wifi
SSID = "ENTER WIFI SSID"
PASSWORD = "ENTER WIFI PASSWORD"

URL = "http://IPADRESWEBSERVER:8080/api/readings"

# Sensor etc
sensor = dht.DHT11(Pin(2))
led = Pin("LED", Pin.OUT)

def blinkleds(amount):
    for i in range(amount):
        led.on()
        sleep(0.5)
        led.off()
        sleep(0.5)

# Connect to wifi
def connect():
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)

    if not wlan.isconnected():
        print("Verbinden met wifi...")
        wlan.connect(SSID, PASSWORD)

        while not wlan.isconnected():
            sleep(1)

    blinkleds(5)
    print("Verbonden met netwerk!")
    print("IP:", wlan.ifconfig()[0])


#Data versturen
def send_data(temperature, humidity):

    payload = {
        "temperature": temperature,
        "humidity": humidity
    }

    response = None

    try:
        response = urequests.post(
            URL,
            data=ujson.dumps(payload),
            headers={
                "Content-Type": "application/json"
            }
        )
        if response.status_code == 201:
            print("HTTP:", response.status_code)
            blinkleds(1)

    except Exception as e:
        blinkleds(2)
        print("POST mislukt:", e)

    finally:
        if response is not None:
            response.close()

#Main loop

connect()

while True:
    try:
        sensor.measure()

        temperature = sensor.temperature()
        humidity = sensor.humidity()

        print("------------------------")
        print("Temperatuur :", temperature, "°C")
        print("Luchtvochtigheid :", humidity, "%")

        send_data(temperature, humidity)

    except Exception as e:
        print("Fout:", e)

    sleep(10)
