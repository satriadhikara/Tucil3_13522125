# Tucil 3 Stima 13522125

> Word Ladder adalah teka-teki atau permainan bahasa yang pertama kali diciptakan oleh Lewis Carroll pada tahun 1877. Dalam permainan ini, pemain diberikan dua kata yang memiliki panjang yang sama dan tugasnya adalah untuk menemukan jalur dari kata pertama ke kata kedua. Contohnya, untuk mengubah kata "COLD" menjadi "WARM", pemain mungkin membuat jalur sebagai berikut: COLD → CORD → CARD → WARD → WARM. Setiap langkah hanya melibatkan perubahan satu huruf, dan setiap kata hasil perubahan harus merupakan kata yang valid. Dalam program ini, akan dibuat program yang menerima dua kata dan mencari jalur dari kata pertama ke kata kedua.

## Table of Contents

- [General Info](#general-information)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Setup](#setup)
- [Usage](#usage)
- [Project Status](#project-status)
- [Contact](#contact)
<!-- * [License](#license) -->

## General Information

This repository is for Tucil 3 Stima 13522125. The project is about solving word ladder problem with Uniform Cost Search, A\* Search, and Greedy Best First Search Algorithm.

## Technologies Used

- Java (openJDK) - version 17
- Springboot - Version 3
- Vite-React

## Features

List the ready features here:

- Solve with Uniform Cost Search Algorithm
- Solve with A\* Search Algorithm
- Solve with Greedy Best First Search Algorithm

## Setup

### Run with Docker

Make sure you have docker installed and pull eclipse-temurin image

```bash
docker pull eclipse-temurin
```

Then run the following command

```bash
docker compose up
```

Then open the browser and go to localhost:5173

Or you can run the backend and frontend separately

Make sure you have maven and npm installed

### Backend

- Open the backend folder
- Run the backend with the following command

```bash
./mvnw spring-boot:run
mwvnw.cmd spring-boot:run
```

### Frontend

- Open the frontnend folder
- Run the frontend with the following command

```bash
npm install
npm run dev
```

Then open the browser and go to localhost:5173

## Usage

There's two input that you can input, the first one is the start word and the second one is the end word. After you input the word, you can choose the algorithm that you want to use to solve the word ladder problem. The algorithm that you can choose are Uniform Cost Search, A\* Search, and Greedy Best First Search. After you choose the algorithm, you can click the solve button to solve the word ladder problem.

It will show the path result based on the algorithm you chose. If the path is not found, it will show that the path is not found.

## Project Status

Project is: _complete_

## Contact

Created by [@satriadhikara](https://github.com/satriadhikara) - feel free to contact me!
