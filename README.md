# GalaxyGaze

GalaxyGaze is an Android application that allows users to view the Astronomy Picture of the Day (APOD) provided by NASA. The app is built using modern Android development practices, including Kotlin, Jetpack Compose, MVVM Clean Architecture, Hilt for dependency injection, and Retrofit for networking. The app also supports offline viewing of previously loaded APOD images and includes swipe-to-refresh functionality.

## Features

- Display the Astronomy Picture of the Day (APOD) with title, date, and description.
- Offline support: Displays the last seen APOD when not connected to the internet.
- Video and Image handling based on media type.
- Swipe-to-refresh for reloading the latest APOD.
- Simple and elegant UI with smooth transitions.
- Error handling and user-friendly error messages.

## Project Structure

The project follows the MVVM Clean Architecture pattern:

- **Domain**: Contains the use cases and repository interfaces.
- **Data**: Contains the repository implementations, network, and local data sources (Retrofit, Room).
- **Presentation**: Contains the UI components, ViewModels, and state management.

## Getting Started

### Prerequisites

- Android Studio Flamingo or later.
- JDK 11 or later.
- Internet connection for fetching the APOD from NASA.

### Setup Instructions

1. Clone the repository:

    ```bash
    git clone https://github.com/shivayogih/GalaxyGaze.git
    cd GalaxyGaze
    ```

2. Open the project in Android Studio.

3. Add your NASA API Key:

    - In `apikeys.properties`, add your NASA API key:

    ```properties
    apiKey=your_api_key_here
    ```

4. Build and run the project:

    - Select your target device/emulator.
    - Click on the "Run" button in Android Studio.

 

## Improvement Areas

- **UI Enhancements**: Add more customization options for the UI, such as themes and layouts.
- **Caching Improvements**: Implement more advanced caching strategies, such as image compression and automatic cache invalidation.
- **Additional Features**: Add functionality for viewing past APODs, bookmarking favorite APODs, and sharing images directly from the app.
- **Localization**: Support for multiple languages.
- **Accessibility**: Improve accessibility by adding content descriptions for images and supporting screen readers.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.


