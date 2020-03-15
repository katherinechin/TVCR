# The Virtual Card Reader

### Created by Boxin Cao, Katherine Chin, & Shahzeb Khurshid
### Team 1: CS680 Spring 2020

## Overview
Utilizing smartphones’ NFC readers, **The Virtual Card Reader (TVCR)** collects data from identification cards embedded with NFC tags, and the data is populated into a contacts list. Our goal is to provide a simple, efficient way for professionals to exchange contact information. Examples of embedded identification cards are business cards and student identification cards. An interactive prototype of the app can be found [here](https://bit.ly/2PHaLrw).

## Flow

### Flow Chart
![Flow Chart](https://github.com/katherinechin/TVCR/blob/assets/flow.png width="750")

The app has two starting points. For Start Point 1, tapping the NFC-enabled device on an NFC identification card will automatically open the application. Populated with the NFC tag’s data, a new contact information page will be saved. Alternatively, for Start Point 2, opening the application on a smartphone will display the home page.

### NFC Tag Notification Alert
![NFC Tag Notification Alert](https://github.com/katherinechin/TVCR/blob/assets/alert.png width="1000")

### Home Page
![Home Page](https://github.com/katherinechin/TVCR/blob/assets/home.png)

The Home activity page lists contacts previously scanned from NFC identification cards. Additionally, the Home page has an Action Bar with widgets. There is a “Recently Opened” widget that directs to the Recently Opened activity page and a “Search” widget for filtering contacts in the list.

### Contact Information Page
![Contact Information Page](https://github.com/katherinechin/TVCR/blob/assets/contact.png width="1000")

Selecting a contact from the Home list will direct to the Contact Information activity page that displays “Number,” “Email,” and “LinkedIn”, and “Address” clickable widgets. Each will use an implicit intent to call an activity that performs dial, text, navigate, mail, or browse functions. When the “Number” widget is tapped, a context menu will display options to call, message, or cancel.

### Recently Opened Page
![Recently Opened Page](https://github.com/katherinechin/TVCR/blob/assets/recents.png width="750")

The Recently Opened Activity page will show the most recently opened contact information pages in a list. Both this page and the Contact Information page will have a back button widget that brings the user back to the Home page.

## Activities
*Home Page* - displays ListView of saved contacts
<br/>*Recently Opened* - displays ListView of contacts starting with last opened first
<br/>*Contact Information* - individual contact information displaying
<br/>*Dialer* - call contact’s number
<br/>*SMS Messaging* - create new text message with contact’s number in recipient field
<br/>*Email* - create new email with contact’s email address in recipient field
<br/>*Browser* - uses contact’s URL to display LinkedIn profile using a built-in browser
<br/>*Google Maps* - built-in navigation to contact’s address

## NFC Technology
Near Field Communication (NFC) is a wireless technology that allows smartphones to share data bidirectionally with other NFC-enabled devices in close proximity of a distance less than 4 cm. Our app will populate and save contact information from an NFC tag. The connection is free and does not rely on Wi-Fi, 3G, or LTE.

### Examples of NFC Technology
[Samsung Pay/Apple Pay/Google Pay](https://www.cnet.com/news/apple-pay-google-pay-samsung-pay-best-mobile-payment-system-compared-nfc/)
<br/>[Sony’s Smart Tags for Sony Xperia Z](https://www.youtube.com/watch?v=w54ORaa754o)
<br/>[Express Transit Suica card (Japanese metro card) on smartphone](https://support.apple.com/en-us/HT207154)
<br/>[Wireless charging using NFC on Mercedes-Benz S-class](https://www.youtube.com/watch?v=LUVIFB1-vq4)
<br/>[NFC-Capable Sony music player](https://www.youtube.com/watch?v=bSJTnv8f-Zs)
<br/>['My Number Cards'](https://appleinsider.com/articles/19/06/11/japanese-iphone-users-will-be-able-to-access-my-number-cards-via-nfc-this-fall)
