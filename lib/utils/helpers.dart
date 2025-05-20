import 'package:intl/intl.dart';

class Helpers {
  // Formats a DateTime object into a readable string
  static String formatDate(DateTime date) {
    final DateFormat formatter = DateFormat('yyyy-MM-dd HH:mm');
    return formatter.format(date);
  }

  // Truncates a string to a specified length and adds an ellipsis
  static String truncateString(String text, int length) {
    if (text.length <= length) {
      return text;
    }
    return '${text.substring(0, length)}...';
  }

  // Capitalizes the first letter of a string
  static String capitalize(String text) {
    if (text.isEmpty) {
      return text;
    }
    return text[0].toUpperCase() + text.substring(1).toLowerCase();
  }

  // Checks if a string is a valid email address
  static bool isValidEmail(String email) {
    final RegExp emailRegex = RegExp(r'^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$');
    return emailRegex.hasMatch(email);
  }
}