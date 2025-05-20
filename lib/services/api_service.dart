import 'dart:convert';
import 'package:http/http.dart' as http;
import '/models/news_article.dart';
import '/utils/constants.dart';
import 'package:flutter/foundation.dart'; // For debugPrint

class ApiService {
  Future<List<NewsArticle>> fetchNews() async {
    // Set a past date range to ensure articles are available
    final now = DateTime.now();
    final from = now.subtract(const Duration(days: 30));
    final url = Uri.parse(
      'https://newsapi.org/v2/everything?q=hockey&from=${from.toIso8601String().split('T')[0]}&apiKey=${Constants.newsApiKey}',
    );
    final response = await http.get(url);

    debugPrint('Status code: ${response.statusCode}');
    debugPrint('Response body: ${response.body}');

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      debugPrint('Total results: ${data['totalResults']}');
      List articles = data['articles'];
      return articles.map((article) => NewsArticle.fromJson(article)).toList();
    } else {
      throw Exception('Failed to load news: ${response.statusCode}');
    }
  }
}
