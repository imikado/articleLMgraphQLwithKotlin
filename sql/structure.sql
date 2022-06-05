CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `author_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,

    PRIMARY KEY (`id`)
) ;

CREATE TABLE `authors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(150) NOT NULL,
  `lastname` varchar(150) NOT NULL,

    PRIMARY KEY (`id`)
) ;

CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,

    PRIMARY KEY (`id`)
) ;