import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '/providers/news_provider.dart';
import '/widgets/news_card.dart';

class NewsScreen extends StatelessWidget {
  const NewsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final newsProvider = Provider.of<NewsProvider>(context);

    // Trigger fetchNews after the first build if articles are empty and not loading
    if (!newsProvider.isLoading && newsProvider.articles.isEmpty && newsProvider.errorMessage == null) {
      WidgetsBinding.instance.addPostFrameCallback((_) {
        newsProvider.fetchNews();
      });
    }

    return Scaffold(
      appBar: AppBar(title: const Text('News & Updates')),
      body: newsProvider.isLoading
          ? const Center(child: CircularProgressIndicator())
          : newsProvider.errorMessage != null
              ? Center(child: Text('Error: ${newsProvider.errorMessage}'))
              : newsProvider.articles.isEmpty
                  ? const Center(child: Text('No news articles available.'))
                  : ListView.builder(
                      itemCount: newsProvider.articles.length,
                      itemBuilder: (context, index) {
                        return NewsCard(article: newsProvider.articles[index]);
                      },
                    ),
    );
  }
}