class Event {
  final String id;
  final String name;
  final DateTime date;
  final String venue;

  Event({required this.id, required this.name, required this.date, required this.venue});

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'date': date.toIso8601String(),
      'venue': venue,
    };
  }

  factory Event.fromMap(Map<String, dynamic> map) {
    return Event(
      id: map['id'],
      name: map['name'],
      date: DateTime.parse(map['date']),
      venue: map['venue'],
    );
  }
}