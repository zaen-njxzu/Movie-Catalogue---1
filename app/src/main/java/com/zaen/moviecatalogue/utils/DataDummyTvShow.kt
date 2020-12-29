package com.zaen.moviecatalogue.utils

import com.zaen.moviecatalogue.models.Movie
import com.zaen.moviecatalogue.models.MovieDetail
import com.zaen.moviecatalogue.utils.Constants.BASE_IMAGE_URL

object DataDummyTvShow {

    fun getTvShows(): ArrayList<Movie> {
        val tvShows = ArrayList<Movie>()

        tvShows.add(Movie(
            100,
            "I Am Not an Animal",
            "${BASE_IMAGE_URL}/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg"
        ))

        tvShows.add(Movie(
            88040,
            "Given",
            "${BASE_IMAGE_URL}/pdDCcAq8RNSZNk81PXYoHNUPHjn.jpg"
        ))

        tvShows.add(Movie(
            83097,
            "The Promised Neverland",
            "${BASE_IMAGE_URL}/yxdeII5tI8qqiERcMxjW9DfB6Gz.jpg"
        ))

        tvShows.add(Movie(
            83095,
            "The Rising of the Shield Hero",
            "${BASE_IMAGE_URL}/ftavpq2PJn5pyo5opJEmj8QleKD.jpg"
        ))

        tvShows.add(Movie(
            96316,
            "Rent-a-Girlfriend",
            "${BASE_IMAGE_URL}/4rqyyM0R3a0EeSlEvdpxDKbjiKB.jpg"
        ))

        tvShows.add(Movie(
            72636,
            "Made In Abyss",
            "${BASE_IMAGE_URL}/uVK3H8CgtrVgySFpdImvNXkN7RK.jpg"
        ))

        tvShows.add(Movie(
            80564,
            "Banana Fish",
            "${BASE_IMAGE_URL}/1UV5di9UIXwrpCW3xQ4RNli5hEV.jpg"
        ))

        tvShows.add(Movie(
            93019,
            "ORESUKI: Are you the only one who loves me?",
            "${BASE_IMAGE_URL}/4MojZik5N62IShd2BFVEHyaRBLP.jpg"
        ))

        tvShows.add(Movie(
            67389,
            "Golden Time",
            "${BASE_IMAGE_URL}/qp4eHCZMR14dtubvzr67PZqH5fa.jpg"
        ))

        tvShows.add(Movie(
            65930,
            "My Hero Academia",
            "${BASE_IMAGE_URL}/he1aoyFEPYAMsjGOVGfObbEIzid.jpg"
        ))

        tvShows.add(Movie(
            68129,
            "Yuri!!! on Ice",
            "${BASE_IMAGE_URL}/oKVFf2uNCLMsovWBxAW14MmhHUm.jpg"
        ))

        return tvShows
    }

    fun getTvShowsDetail() : ArrayList<MovieDetail> {
        val tvShowsDetail = ArrayList<MovieDetail>()

        tvShowsDetail.add(
            MovieDetail(
            100,
            "I Am Not an Animal",
            "${BASE_IMAGE_URL}/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
            9.4,
            "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
            "2004-05-10"
        ))

        tvShowsDetail.add(MovieDetail(
            88040,
            "Given",
            "${BASE_IMAGE_URL}/pdDCcAq8RNSZNk81PXYoHNUPHjn.jpg",
            9.2,
            "Tightly clutching his Gibson guitar, Mafuyu Satou steps out of his dark apartment to begin another day of his high school life. While taking a nap in a quiet spot on the gymnasium staircase, he has a chance encounter with fellow student Ritsuka Uenoyama, who berates him for letting his guitar's strings rust and break. Noticing Uenoyama's knowledge of the instrument, Satou pleads for him to fix it and to teach him how to play. Uenoyama eventually agrees and invites him to sit in on a jam session with his two band mates: bassist Haruki Nakayama and drummer Akihiko Kaji. Satou's voice is strikingly beautiful, filling Uenoyama with the determination to make Satou the lead singer of the band. Though reticent at first, Satou takes the offer after an emotional meeting with an old friend. With the support of his new friends, Satou must not only learn how to play guitar, but also come to terms with the mysterious circumstances that led him to be its owner.",
            "2019-07-11"
        ))

        tvShowsDetail.add(MovieDetail(
            83097,
            "The Promised Neverland",
            "${BASE_IMAGE_URL}/yxdeII5tI8qqiERcMxjW9DfB6Gz.jpg",
            9.2,
            "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \"Mama,\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again. However, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama.",
            "2019-01-09"
        ))

        tvShowsDetail.add(MovieDetail(
            83095,
            "The Rising of the Shield Hero",
            "${BASE_IMAGE_URL}/ftavpq2PJn5pyo5opJEmj8QleKD.jpg",
            9.1,
            "Iwatani Naofumi was summoned into a parallel world along with 3 other people to become the world's Heroes. Each of the heroes respectively equipped with their own legendary equipment when summoned, Naofumi received the Legendary Shield as his weapon. Due to Naofumi's lack of charisma and experience he's labeled as the weakest, only to end up betrayed, falsely accused, and robbed by on the third day of adventure. Shunned by everyone from the king to peasants, Naofumi's thoughts were filled with nothing but vengeance and hatred. Thus, his destiny in a parallel World begins...",
            "2019-01-09"
        ))

        tvShowsDetail.add(MovieDetail(
            96316,
            "Rent-a-Girlfriend",
            "${BASE_IMAGE_URL}/4rqyyM0R3a0EeSlEvdpxDKbjiKB.jpg",
            9.0,
            "In today’s Japan, \"rental\" services can deliver an afternoon with a \"friend,\" a \"parent,\" even a fake girlfriend! Kinoshita Kazuya is a 20-year-old failure of a college student. He managed to kiss his girlfriend once, but was dumped after a month. Completely spiteful, Kazuya gets just desperate enough to give it a try. But he quickly discovers how complicated it can be to \"rent\" an emotional connection… and his new \"girlfriend,\" Mizuhara Chizuru, who’s trying to keep her side hustle secret, will panic when she finds out her real life and Kazuya’s are intertwined in surprising ways! A reckless rom-com filled with love and excitement is about to begin!",
            "2020-07-11"
        ))

        tvShowsDetail.add(MovieDetail(
            72636,
            "Made In Abyss",
            "${BASE_IMAGE_URL}/uVK3H8CgtrVgySFpdImvNXkN7RK.jpg",
            9.0,
            "The “Abyss” is the last unexplored region, an enormous and treacherous cave system filled with ancient relics and strange creatures. Only the bravest of adventurers can traverse its depths, earning them the nickname, “Cave Raiders.” Within the depths of the Abyss, a girl named Riko stumbles upon a robot who looks like a young boy. Tantalized by the Abyss, Riko and her new friend descend into uncharted territory to unlock its mysteries, but what lies in wait for them in the darkness?",
            "2017-07-07"
        ))

        tvShowsDetail.add(MovieDetail(
            80564,
            "Banana Fish",
            "${BASE_IMAGE_URL}/1UV5di9UIXwrpCW3xQ4RNli5hEV.jpg",
            9.0,
            "Nature made Ash Lynx beautiful; nurture made him a cold ruthless killer. A runaway brought up as the adopted heir and sex toy of \"Papa\" Dino Golzine, Ash, now at the rebellious age of seventeen, forsakes the kingdom held out by the devil who raised him. But the hideous secret that drove Ash's older brother mad in Vietnam has suddenly fallen into Papa's insatiably ambitious hands—and it's exactly the wrong time for Eiji Okamura, a pure-hearted young photographer from Japan, to make Ash Lynx's acquaintance...",
            "2018-07-06"
        ))

        tvShowsDetail.add(MovieDetail(
            93019,
            "ORESUKI: Are you the only one who loves me?",
            "${BASE_IMAGE_URL}/4MojZik5N62IShd2BFVEHyaRBLP.jpg",
            8.9,
            "Kisaragi Amatsuyu is invited out alone by the cool beauty upperclassman Cosmos and his childhood friend Himawari. Expecting to hear their confessions, he triumphantly goes to meet each of them in turn. But Cosmos and Himawari both instead confess to Amatsuyu that they like his friend. Amatsuyu fights this lonely battle, but there is another girl who is looking at him. She is a gloomy girl with glasses and braids. Amatsuyu finds that he hates her, because she's always turning her sharp tongue only on him and finding enjoyment in his troubles. But it turns out that she's the only one who actually does like him.",
            "2019-10-03"
        ))

        tvShowsDetail.add(MovieDetail(
            67389,
            "Golden Time",
            "${BASE_IMAGE_URL}/qp4eHCZMR14dtubvzr67PZqH5fa.jpg",
            8.9,
            "Banri Tada is a freshman at a Tokyo law school. After an accident, he suffers severe memory loss. Despite the incident, he befriends fellow freshman, Mitsuo Yanagisawa, which leads him to the beautiful, yet obsessive, Kouko Kaga.",
            "2013-10-04"
        ))

        tvShowsDetail.add(MovieDetail(
            65930,
            "My Hero Academia",
            "${BASE_IMAGE_URL}/he1aoyFEPYAMsjGOVGfObbEIzid.jpg",
            8.9,
            "In a world where eighty percent of the population has some kind of super-powered Quirk, Izuku was unlucky enough to be born completely normal. But that won’t stop him from enrolling in a prestigious hero academy. Now, he’ll get his first taste of brutal rivalry from other schools as he braves the cutthroat, no-holds-barred provisional license exam.",
            "2016-04-03"
        ))

        tvShowsDetail.add(MovieDetail(
            68129,
            "Yuri!!! on Ice",
            "${BASE_IMAGE_URL}/oKVFf2uNCLMsovWBxAW14MmhHUm.jpg",
            8.9,
            "Yūri Katsuki carried all of Japan's hopes on his shoulders to win at the Gran Prix Finale ice skating competition, but suffered a crushing defeat. He returns home to Kyushu and half feels like he wants to retire, and half feels like he wants to continue ice skating. Suddenly the five-time consecutive world championship ice skater Victor Nikiforov appears before him with Yuri Plisetsky, a young Russian figure skater who is already defeating his seniors. Victor and both Yuris take up the challenge on an unprecedented Gran Prix series.",
            "2016-10-06"
        ))

        return tvShowsDetail
    }

    fun getTvShowDetails(id: Int) : MovieDetail? {
        val tvShowsDetail = getTvShowsDetail()
        return tvShowsDetail.find { movieDetail -> movieDetail.id == id }
    }

}