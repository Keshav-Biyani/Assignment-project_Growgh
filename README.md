

# Growigh

**Growigh** is a social media app built using **Kotlin** and **XML**. It allows users to scroll through images and videos in a feed, like posts, and share content with other apps. This app demonstrates the integration of several Android features, including **Google Maps API**, **RecyclerView** for feeds, **Volley** for API access, and more.

## Features

- **Scrollable Image and Video Feed**: Users can scroll through a feed containing images and videos.
- **Like and Dislike Functionality with Animations**: Interactive animations for users to like or dislike posts.
- **Share Functionality**: Users can easily share posts to other apps using a horizontal swipe gesture.
- **Google Maps Integration**: Integrated Google Maps for displaying locations within the app.
- **Image Upload**: Users can upload images from their local storage to share within the app.
- **Bottom Navigation Bar**: Navigate easily between different sections of the app using the Bottom Navigation Bar.
- **RecyclerView for Feed**: Efficiently displays the feed using RecyclerView.
- **Feed Data from API**: The app fetches feed content from the [Pexels API](https://api.pexels.com/v1/curated/?page=1&per_page=10).
- **Shared Preferences**: Onboarding screens are shown only to first-time users, and user preferences are stored locally using Shared Preferences.
- **Splash Screen and Onboarding**: Includes a splash screen and onboarding screens to guide new users through the app features.

## Screenshots

|  ![IMG-20241017-WA0029](https://github.com/user-attachments/assets/7595eb3b-901d-4528-9462-5bc0502e69e7)| ![IMG-20241017-WA0030](https://github.com/user-attachments/assets/dbd885d2-57fa-46d7-9e08-9a31a5271ce5) | ![IMG-20241017-WA0032](https://github.com/user-attachments/assets/49077234-2a1c-4dac-b172-82de5f86ed10) |
|:--:|:--:|:--:|
| ![IMG-20241017-WA0033](https://github.com/user-attachments/assets/01fa64fe-935e-4df4-af10-555cb6b6ae0a) | ![IMG-20241017-WA0031](https://github.com/user-attachments/assets/7189ceba-7437-43df-84fd-7b4335829a20) | ![IMG-20241017-WA0034](https://github.com/user-attachments/assets/5a009a44-a37a-4e09-8c99-90ac5d00fa3e) |
| ![IMG-20241017-WA0035](https://github.com/user-attachments/assets/9bce7202-794a-40ab-b966-66dbb80d34ec) | ![IMG-20241017-WA0037](https://github.com/user-attachments/assets/2ce339aa-434e-435e-91cf-17825837da32) | ![IMG-20241017-WA0036](https://github.com/user-attachments/assets/38091926-341c-495d-80bf-0f86b54f4b95) |
| ![IMG-20241017-WA0038](https://github.com/user-attachments/assets/81cab529-3135-4f45-8165-79aa8c016a11) |  |  |

## API Usage

The app uses the Pexels API to fetch curated image and video content for the feed.

API Endpoint:
```
https://api.pexels.com/v1/curated/?page=1&per_page=10
```

You will need to add your Pexels API key in your project securely using environment variables or Android's secure key storage methods.

## Libraries and Tools Used

- **Kotlin**: Main programming language used for development.
- **XML**: Used for designing user interface layouts.
- **Google Maps API**: Displays and interacts with maps in the app.
- **RecyclerView**: Displays a scrollable list of posts (images and videos) in the feed.
- **Volley**: Handles API requests to fetch the data from Pexels.
- **Bottom Navigation**: Simple navigation across different app sections.
- **Shared Preferences**: Used to save user data locally, such as onboarding completion status.
- **Animation**: Added animations for post like/dislike functionality.
- **Toast**: Provides feedback for various user actions like image uploads and shares.

## Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/Keshav-Biyani/Growigh.git
   ```
2. Open the project in Android Studio.
3. Securely add your Pexels API key in the necessary location. Ensure not to hard-code it directly into your project files.
4. Build and run the project on an emulator or Android device.

## Usage

- **Onboarding**: The app starts with a splash screen, followed by onboarding screens for new users.
- **Feed**: Scroll through a curated feed of images and videos from the Pexels API.
- **Like/Dislike**: Users can like or dislike posts with animations for an enhanced experience.
- **Share**: Posts can be shared with other apps using a simple horizontal swipe gesture.
- **Upload Images**: Users can upload images from local storage.
- **Map View**: Use the Google Maps feature to view and interact with specific locations.

## Contributing

Contributions are welcome! Feel free to fork this repository, create issues, and submit pull requests to improve the app.

--- 
