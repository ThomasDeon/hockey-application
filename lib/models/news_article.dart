class NewsArticle {
  final String title;
  final String source;
  final String? imageUrl;
  final String? description;
  final String? url;

  NewsArticle({
    required this.title,
    required this.source,
    this.imageUrl,
    this.description,
    this.url,
  });

  factory NewsArticle.fromJson(Map<String, dynamic> json) {
    return NewsArticle(
      title: json['title'] ?? 'No title',
      source: json['source']['name'] ?? 'Unknown source',
      imageUrl: json['urlToImage'],
      description: json['description'],
      url: json['url'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'title': title,
      'source': source,
      'imageUrl': imageUrl,
      'description': description,
      'url': url,
    };
  }
}