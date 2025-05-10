package com.example.hockeyapp.ui.newsPages


// this class will show or display the news that is current on the hocky association website

data class LocalHockeyNews(
    val title: String,
    val description: String,
    val imageUrl: String,
    val articleUrl: String
)

val newsList = listOf(
    LocalHockeyNews(
        title = "A Namibian hockey giant departs",
        description = "NAMIBIA’S hockey community was still struggling to come to terms with the news that its president Marc Nel had passed away due …",
        imageUrl = "https://namibiahockey.org/wp-content/uploads/2021/08/MarcNel.jpg",
        articleUrl = "https://namibiahockey.org/2021/06/28/a-namibian-hockey-giant-departs/"
    ),
    LocalHockeyNews(
        title = "Watch out world, Namibia and South Africa are on their way",
        description = "South Africa men and Namibia women have secured their places at the FIH Indoor Hockey World Cup in Belgium next year after …",
        imageUrl = "https://namibiahockey.org/wp-content/uploads/2021/08/namibia_women.png",
        articleUrl = "https://namibiahockey.org/2021/04/18/watch-out-world-namibia-and-south-africa-are-on-their-way/"
    ),

    LocalHockeyNews(
        title = "Dream Come True-Nel",
        description = "It’s a dream come true”. That’s how Namibia Hockey Union president Marc Nel has described the three-million-dollar sponsorship from MTC.Mobile communications giant …",
        imageUrl = "https://namibiahockey.org/wp-content/uploads/2021/08/NHU-3.png",
        articleUrl = "https://namibiahockey.org/2020/07/07/dream-come-true-nel/"
    ),
    LocalHockeyNews(
        title = "NEWS",
        description = "The Indoor African Cup will take place in Thomas Moore College, Kloof., South Africa on 16-18 April. The reward for the winning men’s …",
        imageUrl = "https://namibiahockey.org/wp-content/uploads/2021/08/ahf_indoorchamp21_nagra_news.png",
        articleUrl = "https://namibiahockey.org/2021/04/13/news/"
    ),

    LocalHockeyNews(
        title = "Semifinal lineups completed in Bank Windhoek Outdoor League",
        description = "The final semifinal spots in the Bank Windhoek Outdoor Hockey League were decided following the last round of league matches on Wednesday …",
        imageUrl = "https://namibiahockey.org/wp-content/uploads/2020/12/nhu-2-1024x682.jpg",
        articleUrl = "https://namibiahockey.org/2020/12/21/semifinal-lineups-completed-in-bank-windhoek-outdoor-league/"
    ),
    LocalHockeyNews(
        title = "Exciting Hockey was played at the Outdoor Night league finals 2020",
        description = "Windhoek Old Boys are Namibia’s new Bank Windhoek Men’s First League hockey champions, after beating the defending champions Saints 1-0 in a …",
        imageUrl = "https://namibiahockey.org/wp-content/uploads/2020/12/nhu-1.jpg",
        articleUrl = "https://namibiahockey.org/2020/12/21/exciting-hockey-was-played-at-the-outdoor-night-league-finals-2020/"
    ),
)