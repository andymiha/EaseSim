# EaseSim

## Authors
40184667  -  Sarah Abellard  
40101308  -  Mehdi Fouzail  
40175868  -  Sarah Malik  
40139389  -  Andrei Mihaescu  
40083837  -  Jonathan Pasquin  
40258189  -  Noura Tabbara  

## Pre-requisites
- Docker Desktop
- yarn
- git

## Setup Instructions
1. Clone this repository.
2. Open Docker Desktop.
3. Open your repository path in your terminal.
4. Run `docker-compose up -d` to start and build all services.
5. go to `\backend\build\libs` folder and copy snapshot jar file to `backend` folder
6. Rename the snapshot folder to `app` (should remain a jar file)
7. In `\backend`, run `sh gradlew clean bootJar`


TO BE FIXED. Run `yarn setup-frontend` for initial frontend configuration.

### Accessing the Frontend
8. Go to [http://localhost:4123] to access EaseSim.
9. Go  to [http://localhost:4024] to access Backend

### Accessing Command Line Interfaces
- You can access the frontend command line using `yarn ssh frontend`.
- You can access the backend command line using `yarn ssh backend`.

