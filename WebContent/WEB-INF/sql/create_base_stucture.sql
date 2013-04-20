CREATE DATABASE IF NOT EXISTS `webappdb` DEFAULT CHARACTER SET utf8;

USE `webappdb`;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`title`) values (1,'teacher'),(2,'student');




/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `idRole` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_user_role` (`idRole`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`idRole`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`email`,`password`,`idRole`) values (1,'qqq@mail.ru','qqq',2),(2,'aaa@mail.ru','aaa',2),(3,'zzz@mail.ru','zzz',2),(4,'www@mail.ru','www',2),(5,'sss@mail.ru','sss',2),(6,'xxx@mail.ru','xxx',1),(7,'eee@mail.ru','eee',1),(8,'ddd@mail.ru','ddd',1),(9,'ccc@ya.ru','ccc',2),(10,'rrr@mail.ru','rrr',2),(11,'fff@gmail.com','fff',2),(12,'vvv@mail.ru','vvv',2),(13,'ttt@gmail.com','ttt',2),(14,'ggg@mail.ru','ggg',2),(15,'bbb@mail.ru','bbb',2),(16,'yyy@gmail.com','yyy',2),(17,'hhh@mail.ru','hhh',2),(18,'nnn@mail.ru','nnn',2),(19,'uuu@mail.ru','uuu',2),(20,'jjj@mail.ru','jjj',2),(21,'mmm@mail.ru','mmm',2),(22,'iii@mail.ru','iii',2),(23,'kkk@gmail.com','kkk',2),(24,'ooo@gmail.com','ooo',2),(25,'lll@gmail.com','lll',2),(26,'ppp@gmail.com','ppp',2),(31,'new@mail.ru','new',2),(32,'qwe@mail.ru','qwe',2),(33,'asd@mail.ru','asd',1),(37,'bnm@mail.ru','bnm',1),(38,'kij@mail.ru','kij',2),(39,'ygt@mail.ru','ygt',2);




/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullName` varchar(255) NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`),
  KEY `fk_student_user1` (`idUser`),
  CONSTRAINT `fk_student_user1` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`fullName`,`idUser`) values (1,'Лиходиевский Александр Владимирович',1),(2,'Тихон Олег Игоревич',2),(3,'Федюкович Илья Александрович',3),(4,'Киреенко Вероника Сергеевна',11),(5,'Чопчиц Александр Сергеевич',5),(6,'Ясеня Роман Викторович',9),(7,'Космич Дмитрий Иванович',15),(8,'Липская Ольга Игоревна',16),(9,'Савин Никита Сергеевич',17),(10,'Сейко Олег Сергеевич',18),(11,'Берникович Тимур Ярославович',19),(12,'Грук Егор Игоревич',20),(13,'Шульгат Виктор Николаевич',10),(14,'Юрковский Роман Владимирович',4),(15,'Абрамов Павел Андреевич',12),(16,'Волкова Ольга Андреевна',13),(17,'Корбут Антон Николаевич',14),(18,'Коваль Егор Владимирович',21),(19,'Фонасова Надежда Сергеевна',22),(20,'Юрча Илья Игоревич',23),(21,'Ягмин Кирилл Олегович',24),(22,'Ардельянов Валентин Сергеевич',25),(23,'Бондарь Павел Андреевич',26),(25,'Фамусов Игорь Петрович',31),(26,'Давыденко Максим Иванович',32),(27,'Василевский Роман Игоревич',38),(28,'Черняховский Петр Андреевич',39);




/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullName` varchar(255) NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`),
  KEY `fk_teacher_user1` (`idUser`),
  CONSTRAINT `fk_teacher_user1` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`fullName`,`idUser`) values (1,'Прохоров Николай Андреевич',7),(2,'Дробот Никита Александрович',6),(3,'Янкович Дмитрий Юрьевич',8),(4,'Пархоменко Ирина Антоновна',33),(5,'Алексеев Антон Петорвич',37);




/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` text,
  `hours` int(11) NOT NULL,
  `idTeacher` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idCourse_UNIQUE` (`id`),
  KEY `fk_Course_Teacher1` (`idTeacher`),
  CONSTRAINT `fk_Course_Teacher1` FOREIGN KEY (`idTeacher`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`title`,`description`,`hours`,`idTeacher`) values (1,'Java','Курс предназначен для всех, кто желает изучить фундаментальные элементы мощной кроссплатформенной технологии Java: студентов, специализирующихся в области разработки программного обеспечения, действующих инженеров-программистов для перехода на новую технологию, специалистов, желающих повысить квалификацию, пройти профессиональную переподготовку или доподготовку в области IT – инженерии. Цель курса – дать основы Java, необходимые для успешного освоения любой из высокоуровневых технологий семейства Java: от приложений для мобильных устройств до многопользовательских корпоративных систем и Интернет-приложений.  Важная составляющая часть курса – акцент на практическое применение полученных навыков.',102,1),(2,'С++','В современном мире программирование является рутинным занятием учёного, будь то обработка экспериментальных данных или теоретические расчёты. В физике и особенно в физике высоких энергий возросшая сложность экспериментов привела к доминированию программного обеспечения написанного на языке C++. Язык С++, обладая несомненной гибкостью и мощностью заложенных в него концепций, сложен в изучении, особенно для начинающих. Поэтому курс строится из двух частей. Первая часть содержит изложение С-подмножества языка С++ и знакомит студентов с основными приёмами программирования. Во второй части даётся сравнительно полное описание языка С++ и студенты знакомятся с объектно-ориентированном программированием.',60,2),(4,'.NET','.NET Framework — программная платформа, выпущенная компанией Microsoft в 2002 году. Основой платформы является исполняющая среда Common Language Runtime (CLR), способная выполнять как обычные программы, так и серверные веб-приложения. .NET Framework поддерживает создание программ, написанных на разных языках программирования.',88,1),(9,'Erlang777','Erlang — Э́рланг — функциональный язык программирования с динамической типизацией, предназначенный для создания распределённых вычислительных систем. Разработан и поддерживается компанией Ericsson. Язык включает в себя средства порождения параллельных процессов и их коммуникации с помощью асинхронных сообщений и в соответствии с моделью акторов. Программа транслируется в байт-код, исполняемый виртуальной машиной, что обеспечивает переносимость. Кратко формулу языка можно выразить как Erlang=функциональный язык + процессы.',78,3),(10,'Lisp','(LISP, от англ. LISt Processing language — «язык обработки списков»; современное написание: Lisp) — семейство языков программирования, программы и данные в которых представляются системами линейных списков символов. Лисп является вторым в истории (после Фортрана) используемым по сей день высокоуровневым языком программирования. Создатель Лиспа Джон Маккарти занимался исследованиями в области искусственного интеллекта (в дальнейшем ИИ) и созданный им язык по сию пору является одним из основных средств моделирования различных аспектов ИИ.',84,3),(11,'Brainfuck','Brainfuck (англ. brain мозг + fuck) — один из известнейших эзотерических языков программирования, придуман Урбаном Мюллером (нем. Urban Müller) в 1993 году для забавы. Язык имеет восемь команд, каждая из которых записывается одним символом. Исходный код программы на Brainfuck представляет собой последовательность этих символов без какого-либо дополнительного синтаксиса.\r\nОдним из мотивов Урбана Мюллера было создание языка с как можно меньшим компилятором. Отчасти он был вдохновлён языком FALSE, для которого существовал компилятор размера 1024 байта. Существуют компиляторы языка Brainfuck размера меньше 200 байт. Программы на языке Brainfuck писать сложно, за что его иногда называют языком для мазохистов. Но при этом важно отметить, что Brainfuck является вполне естественным, полным и простым языком и может использоваться при определении понятия вычислимости.\r\nМашина, которой управляют команды Brainfuck, состоит из упорядоченного набора ячеек и указателя текущей ячейки, напоминая ленту и головку машины Тьюринга. Кроме того, подразумевается устройство общения с внешним миром (см. команды . и ,) через поток ввода и поток вывода.',60,3),(12,'Scala','Scala — мультипарадигмальный язык программирования, спроектированный кратким и типобезопасным для простого и быстрого создания компонентного программного обеспечения, сочетающий возможности функционального и объектно-ориентированного программирования.\r\nПервые версии языка созданы в 2003 году коллективом лаборатории методов программирования Федеральной политехнической школы Лозанны под руководством Мартина Одерского (англ. Martin Odersky), язык реализован для платформ Java и .Net. По мнению Джеймса Стрэчена (англ. James Strachan (programmer)), создателя языка программирования Groovy, Scala может стать преемником языка Java[2].',98,3),(37,'fghghgg','redrhe',7676,3),(38,'Scala','Scala — мультипарадигмальный язык программирования, спроектированный кратким и типобезопасным для простого и быстрого создания компонентного программного обеспечения, сочетающий возможности функционального и объектно-ориентированного программирования.\r\n\r\nПервые версии языка созданы в 2003 году коллективом лаборатории методов программирования Федеральной политехнической школы Лозанны под руководством Мартина Одерского (англ. Martin Odersky), язык реализован для платформ Java и .Net. По мнению Джеймса Стрэчена (англ. James Strachan (programmer)), создателя языка программирования Groovy, Scala может стать преемником языка Java[2].',666,2),(39,'Scala','Scala — мультипарадигмальный язык программирования, спроектированный кратким и типобезопасным для простого и быстрого создания компонентного программного обеспечения, сочетающий возможности функционального и объектно-ориентированного программирования.\r\n\r\nПервые версии языка созданы в 2003 году коллективом лаборатории методов программирования Федеральной политехнической школы Лозанны под руководством Мартина Одерского (англ. Martin Odersky), язык реализован для платформ Java и .Net. По мнению Джеймса Стрэчена (англ. James Strachan (programmer)), создателя языка программирования Groovy, Scala может стать преемником языка Java[2].',666,2),(40,'Scala','Scala — мультипарадигмальный язык программирования, спроектированный кратким и типобезопасным для простого и быстрого создания компонентного программного обеспечения, сочетающий возможности функционального и объектно-ориентированного программирования.\r\n\r\nПервые версии языка созданы в 2003 году коллективом лаборатории методов программирования Федеральной политехнической школы Лозанны под руководством Мартина Одерского (англ. Martin Odersky), язык реализован для платформ Java и .Net. По мнению Джеймса Стрэчена (англ. James Strachan (programmer)), создателя языка программирования Groovy, Scala может стать преемником языка Java[2].',666,2);




/*Table structure for table `archive` */

DROP TABLE IF EXISTS `archive`;

CREATE TABLE `archive` (
  `idCourse` int(11) NOT NULL,
  `mark` int(10) unsigned DEFAULT NULL,
  `idStudent` int(11) NOT NULL,
  KEY `fk_Student_has_Courses_Courses1` (`idCourse`),
  KEY `fk_Archive_Students1` (`idStudent`),
  CONSTRAINT `fk_Archive_Students1` FOREIGN KEY (`idStudent`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_has_Courses_Courses1` FOREIGN KEY (`idCourse`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `archive` */

insert  into `archive`(`idCourse`,`mark`,`idStudent`) values (1,104,1),(1,4,2),(1,10,3),(1,6,4),(2,6,4),(1,155,14),(2,8,14),(1,5,5),(9,8,3),(9,NULL,5),(4,NULL,14),(10,3,14),(2,NULL,5),(11,6,5),(11,NULL,14),(10,NULL,1),(12,NULL,1);
