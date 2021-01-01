package com.zaen.moviecatalogue.utils

import com.zaen.moviecatalogue.source.remote.response.Movie
import com.zaen.moviecatalogue.source.remote.response.MoviesResponse

object DataDummyMovie {

    fun getMovies() : MoviesResponse {
        val movies = MoviesResponse()

        movies.add(Movie(
            529203,
            "The Croods: A New Age",
            "/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg",
            7.8,
            "After leaving their cave, the Croods encounter their biggest threat since leaving: another family called the Bettermans, who claim and show to be better and evolved. Grug grows suspicious of the Betterman parents, Phil and Hope, as they secretly plan to break up his daughter Eep with her loving boyfriend Guy to ensure that their daughter Dawn has a loving and smart partner to protect her. Searching for a safer habitat, the prehistoric Crood family discovers an idyllic, walled-in paradise that meets all of its needs. Unfortunately, they must also learn to live with the Bettermans -- a family that's a couple of steps above the Croods on the evolutionary ladder. As tensions between the new neighbors start to rise, a new threat soon propels both clans on an epic adventure that forces them to embrace their differences, draw strength from one another, and survive together.",
            "2020-11-25"
        ))

        movies.add(Movie(
            553604,
            "Honest Thief",
            "/zeD4PabP6099gpE0STWJrJrCBCs.jpg",
            6.9,
            "A bank robber tries to turn himself in because he's falling in love and wants to live an honest life...but when he realizes the Feds are more corrupt than him, he must fight back to clear his name.",
            "2020-09-03"
        ))

        movies.add(Movie(
            464052,
            "Wonder Woman 1984",
            "/lNVHB85FUDZqLzvug3k6FA07RIr.jpg",
            7.4,
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "2020-12-16"
        ))

        movies.add(Movie(
            577922,
            "Tenet",
            "/k68nPLbIST6NP96JmTxmZijEvCA.jpg",
            7.4,
            "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
            "2020-08-22"
        ))

        movies.add(Movie(
            508442,
            "Soul",
            "/zSuRX1VmT0PwuHVV7zl4n1mwdL0.jpg",
            8.5,
            "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
            "2020-12-25"
        ))

        movies.add(Movie(
            524047,
            "Greenland",
            "/bNo2mcvSwIvnx8K6y1euAc1TLVq.jpg",
            7.1,
            "John Garrity, his estranged wife and their young son embark on a perilous journey to find sanctuary as a planet-killing comet hurtles toward Earth. Amid terrifying accounts of cities getting levelled, the Garrity's experience the best and worst in humanity. As the countdown to the global apocalypse approaches zero, their incredible trek culminates in a desperate and last-minute flight to a possible safe haven.",
            "2020-07-29"
        ))

        movies.add(Movie(
            646593,
            "Wander",
            "/2AwPvNHphpZBJDqjZKVuMAbvS0v.jpg",
            5.5,
            "After getting hired to probe a suspicious death in the small town of Wander, a mentally unstable private investigator becomes convinced the case is linked to the same 'conspiracy cover up' that caused the death of his daughter.",
            "2020-12-04"
        ))

        movies.add(Movie(
            590706,
            "Jiu Jitsu",
            "/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg",
            5.4,
            "Every six years, an ancient order of jiu-jitsu fighters joins forces to battle a vicious race of alien invaders. But when a celebrated war hero goes down in defeat, the fate of the planet and mankind hangs in the balance.",
            "2020-11-20"
        ))

        movies.add(Movie(
            602211,
            "Fatman",
            "/4n8QNNdk4BOX9Dslfbz5Dy6j1HK.jpg",
            5.8,
            "A rowdy, unorthodox Santa Claus is fighting to save his declining business. Meanwhile, Billy, a neglected and precocious 12 year old, hires a hit man to kill Santa after receiving a lump of coal in his stocking.",
            "2020-11-13"
        ))

        movies.add(Movie(
            614911,
            "The Midnight Sky",
            "/51JxCk77ZCqLzbLkrDl9Qho6KUh.jpg",
            6.0,
            "A lone scientist in the Arctic races to contact a crew of astronauts returning home to a mysterious global catastrophe.",
            "2020-12-10"
        ))

        movies.sortBy { movie -> movie.id }

        return movies
    }

    fun getMovieDetail(id: Int) : Movie? {
        val movies = getMovies()
        return movies.find { movie -> movie.id == id }
    }

}