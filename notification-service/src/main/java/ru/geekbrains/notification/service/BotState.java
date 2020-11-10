package ru.geekbrains.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.geekbrains.entity.user.User;
import ru.geekbrains.notification.telegram.TelegramBotContext;

import java.util.Optional;

@Slf4j
public enum  BotState {
    START(false) {
        @Override
        public void enter(TelegramBotContext context) {
            sendMessage(context, "Привет, сейчас мы найдем для вас жилье");
        }

        @Override
        public BotState nextState() {
            return CHECK_AUTH;
        }
    },

    CHECK_AUTH(true) {
        BotState next;
        @Override
        public void enter(TelegramBotContext context) {
            sendMessage(context, "Пожалуйста, введите свой login или зарегистрируйтесь на нашем сайте.");
        }

        @Override
        public void handleInput(TelegramBotContext context) {
            //проверяем логин и связываем id c учетной записью
            log.info("### user input - " + context.getInput());

            Optional<User> user = context.findByLogin(context.getInput());
            if(user.isPresent()){
                context.getUser().setUser(user.get());
                log.info(String.format("### Связываем телеграм с пользователем %s", user.get()));
                next = QUESTION_1;
            } else {
                next = CHECK_AUTH;
                log.info(String.format("### Пользователь с логином %s не найден", context.getInput()));
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    QUESTION_1(true) {
        BotState next;
        @Override
        public void enter(TelegramBotContext context) {
            sendMessage(context, "В какой стране будем искать жилье?");
        }

        @Override
        public void handleInput(TelegramBotContext context) {
            //проверка на валидность
            if(!context.getInput().equals("")){
                context.getUser().getAnswer().setCountry(context.getInput());
                next = QUESTION_2;
            } else {
                next = QUESTION_1;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },


    QUESTION_2(true) {
        BotState next;
        @Override
        public void enter(TelegramBotContext context) {
            sendMessage(context, "В каком городе найти жилье?");
        }

        @Override
        public void handleInput(TelegramBotContext context) {
            //проверка на валидность
            if(!context.getInput().equals("")){
                context.getUser().getAnswer().setCity(context.getInput());
                next = QUESTION_3;
            } else {
                next = QUESTION_2;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    QUESTION_3(true) {
        BotState next;
        @Override
        public void enter(TelegramBotContext context) {
            sendMessage(context, "Сейчас введите количетсво комнат. Напримре: 1 либо 1,2,3 если рассматриваете несколько вариантов");
        }

        @Override
        public void handleInput(TelegramBotContext context) {
            //проверка на валидность
            try {
                int room = Integer.parseInt(context.getInput());
                context.getUser().getAnswer().setRoom(room);
                next = QUESTION_4;
            } catch (Exception e) {
                next = QUESTION_3;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    QUESTION_4(true) {
        BotState next;
        @Override
        public void enter(TelegramBotContext context) {
            sendMessage(context, "Сейчас введите ценовой диапазон. Например: 20000-40000");
        }

        @Override
        public void handleInput(TelegramBotContext context) {
            //проверка на валидность
            String[] prices = context.getInput().split("-");
            try {
                //пока берем только 1 число
                int priceMin = Integer.parseInt(context.getInput());
                context.getUser().getAnswer().setPrice(priceMin);
                next = END;
            } catch (Exception e) {
                next = QUESTION_3;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    END (false) {
        @Override
        public void enter(TelegramBotContext context) {
            //формируем таску и отправляем в rest service
            context.sendAnswer();
            sendMessage(context, "Ищем вариенты ... \n" +
                    context.getUser().getAnswer().toString());
        }

        @Override
        public BotState nextState() {
            return CHECK_AUTH;
        }
    };

    private static BotState[] states;
    private final boolean inputNeeded;

    BotState() {
        this.inputNeeded = true;
    }
    BotState(boolean inputNeeded) {
        this.inputNeeded = true;
    }
    public static BotState getInstance() {
        return byID(0);
    }
    public static BotState byID(int id){
        if(states == null){
            states = BotState.values(); //все значения в массив
        }
        return states[id];
    }
    protected void sendMessage(TelegramBotContext context, String text){
        SendMessage message = new SendMessage()
                .setChatId(context.getUser().getChatId())
                .setText(text);
//                .setReplyMarkup(setButtons());
//        setButtons(message);

        try {
            context.getBot().execute(message);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
//    public synchronized void setButtons(SendMessage message) {
//        // Создаем клавиуатуру
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        message.setReplyMarkup(replyKeyboardMarkup);
//        replyKeyboardMarkup.setSelective(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        replyKeyboardMarkup.setOneTimeKeyboard(false);
//
//        // Создаем список строк клавиатуры
//        List<KeyboardRow> keyboard = new ArrayList<>();
//
//        // Первая строчка клавиатуры
//        KeyboardRow keyboardFirstRow = new KeyboardRow();
//        // Добавляем кнопки в первую строчку клавиатуры
//        keyboardFirstRow.add(new KeyboardButton("Привет"));
//
//        // Вторая строчка клавиатуры
//        KeyboardRow keyboardSecondRow = new KeyboardRow();
//        // Добавляем кнопки во вторую строчку клавиатуры
//        keyboardSecondRow.add(new KeyboardButton("Помощь"));
//
//        // Добавляем все строчки клавиатуры в список
//        keyboard.add(keyboardFirstRow);
//        keyboard.add(keyboardSecondRow);
//        // и устанваливаем этот список нашей клавиатуре
//        replyKeyboardMarkup.setKeyboard(keyboard);
//    }
    public boolean isInputNeeded(){
        return inputNeeded;
    }
    public void handleInput(TelegramBotContext context){
        //когда пользователь что-то присылает срабатывает метод
    }
    public abstract void enter(TelegramBotContext context); //войти в состояние
    public abstract BotState nextState();
}
