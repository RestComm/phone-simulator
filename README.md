# phone-simulator

![USSD-Client](https://user-images.githubusercontent.com/1968058/31864110-619351a4-b715-11e7-90a1-d65b9c3ca925.gif)

This project is to simulate the Mobile Phone to do various demos. For now it will only support USSD Messages. It supports pull and push notification.

*Is work in progress, you are welcome to make this better.

About
========

This is a maven project, it was built using JDK 1.8 using NetBeans and it works with Telscale USSD GW 6.2.1, newer versions of USSD GW should be tested.

The project consist of 3 modules:

map-impl

USSD-Client < This is were the magic happens

simulator-core aka 'core'

*Graphics interface was built using JavaFX

How to run
========

1) You have to modify 'SCTPManagement_sctp.xml' located inside 'TelScale-ussd-6.2.1.257/jboss-5.1.0.GA/server/simulator/data/'

At line 4 replace 'hostAddress' for the IP of the machine running USSD-GW

At line 13 replace 'peerAddress' for the IP of the machine running USSD-Client

2) USSD-Client load it's configuration from 'main_simulator2.xml'

SCTP local host: Is the IP of the machine running USSD-Client

SCTP romete host: Is the IP of the machine running USSD GW

Acknowledgements
========

Thanks to Joram Herrera from Somos Discovery for this great work! :)
