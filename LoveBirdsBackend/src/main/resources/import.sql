insert into lb_question(q_category, q_questiontext)
values (0, 'Would you rather go to the theater or the movies as a couple?');

insert into lb_question (q_category, q_questiontext)
values (0, 'Would you rather argue all night to resolve a conflict or end the argument unresolved before bed?');

insert into lb_answer (a_answertext, question_q_id)
values ('Theater', 1);

insert into lb_answer (a_answertext, question_q_id)
values ('Movies', 1);

insert into lb_answer (a_answertext, question_q_id)
values ('Resolve', 2);

insert into lb_answer (a_answertext, question_q_id)
values ('Go to bed', 2);