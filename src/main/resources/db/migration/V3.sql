INSERT INTO writers (id, firstName, lastName)
VALUES (1, 'Ivan', 'Ivanov'),
       (2, 'Pyotr', 'Petrov'),
       (3, 'Maria', 'Sidorova'),
       (4, 'Anna', 'Kuznetsova'),
       (5, 'Sergey', 'Smirnov');

INSERT INTO posts (id, content, writerId)
VALUES (1, 'My first post about programming in Java. I talk about the basics of OOP.', 1),
       (2, 'Today I want to share my impressions of the new version of Spring Framework.', 1),
       (3, 'The recipe for perfect coffee for a programmer: choosing beans, grind, and brewing.', 3),
       (4, 'Review of the latest trends in web development for 2024.', 2),
       (5, 'How I started learning Python and what came of it. Personal experience.', 4),
       (6, 'SQL query optimization: best practices and common mistakes.', 5),
       (7, 'Setting up Docker for local development: a detailed guide.', 2),
       (8, 'Artificial intelligence in everyday life: what AI can do right now.', 4);

INSERT INTO post_status (id, postId, status)
VALUES (1, 1, 'ACTIVE'),
       (2, 2, 'ACTIVE'),
       (3, 3, 'ACTIVE'),
       (4, 4, 'UNDER_REVIEW'),
       (5, 5, 'ACTIVE'),
       (6, 6, 'UNDER_REVIEW'),
       (7, 7, 'ACTIVE'),
       (8, 8, 'UNDER_REVIEW');

INSERT INTO labels (id, name)
VALUES (1, 'Programming'),
       (2, 'Java'),
       (3, 'Spring'),
       (4, 'Coffee'),
       (5, 'Web Development'),
       (6, 'Python'),
       (7, 'SQL'),
       (8, 'Docker'),
       (9, 'AI'),
       (10, 'Personal Experience'),
       (11, 'Tutorial');

INSERT INTO posts_labels (post_id, label_id)
VALUES (1, 1),
       (1, 2),
       (1, 10),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 4),
       (4, 1),
       (4, 5),
       (5, 1),
       (5, 6),
       (5, 10),
       (6, 1),
       (6, 7),
       (6, 11),
       (7, 1),
       (7, 8),
       (7, 11),
       (8, 1),
       (8, 9);