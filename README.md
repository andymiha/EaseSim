# EaseSim

## Authors

## Pre-requisites
- Docker Desktop
- yarn
- git

## Setup Instructions
1. Clone this repository.
2. Go to the root of this repository.
3. Copy `.env.example` to `.env`.
4. Inside the `frontend` and `backend` folder, copy `.env.example` to `.env`.
5. Go back to the root folder.
6. Run `yarn docker` to start and build all services.
7. Run `yarn setup-backend` for initial backend configuration.
8. Run `yarn setup-frontend` for initial frontend configuration.

### Accessing the Frontend
11. Go to [http://localhost:4123](http://localhost:4123) to access EaseSim.

### Accessing Command Line Interfaces
- You can access the frontend command line using `yarn ssh frontend`.
- You can access the backend command line using `yarn ssh backend`.
