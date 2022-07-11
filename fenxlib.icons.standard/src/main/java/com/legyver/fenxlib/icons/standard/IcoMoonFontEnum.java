package com.legyver.fenxlib.icons.standard;

import java.util.stream.Stream;

/**
 * Icons in the IcoMoon free pack
 * See the collection at <a href=https://icomoon.io/#preview-free>IcoMoon</a>
 * <p>
 * The enum types below are formed by applying the following transformation rules to the IcoMoon glyph names
 * 1. The Name is converted to upper cast to conform with enum conventions
 * 2. Any hyphens ('-') in the names are substituted with underscores ('_') to form valid java variables
 * 3. Any names that start with numbers are replaced with the written number (ex: 500px -> FIVE_HUNDRED_PX) to form valid java variables
 */
public enum IcoMoonFontEnum {
    /**
     * Home icon
     */
    ICON_HOME("icon-home", "\uE900"),
    /**
     * Home icon
     */
    ICON_HOME2("icon-home2", "\uE901"),
    /**
     * Home icon
     */
    ICON_HOME3("icon-home3", "\uE902"),
    /**
     * Office icon
     */
    ICON_OFFICE("icon-office", "\uE903"),
    /**
     * Newspaper icon
     */
    ICON_NEWSPAPER("icon-newspaper", "\uE904"),
    /**
     * Pencil icon
     */
    ICON_PENCIL("icon-pencil", "\uE905"),
    /**
     * Pencil icon
     */
    ICON_PENCIL2("icon-pencil2", "\uE906"),
    /**
     * Quill icon
     */
    ICON_QUILL("icon-quill", "\uE907"),
    /**
     * Pen icon
     */
    ICON_PEN("icon-pen", "\uE908"),
    /**
     * Blog icon
     */
    ICON_BLOG("icon-blog", "\uE909"),
    /**
     * Eyedropper icon
     */
    ICON_EYEDROPPER("icon-eyedropper", "\uE90a"),
    /**
     * Droplet icon
     */
    ICON_DROPLET("icon-droplet", "\uE90b"),
    /**
     * Paint format icon
     */
    ICON_PAINT_FORMAT("icon-paint-format", "\uE90c"),
    /**
     * Image icon
     */
    ICON_IMAGE("icon-image", "\uE90d"),
    /**
     * Images icon
     */
    ICON_IMAGES("icon-images", "\uE90e"),
    /**
     * Camera icon
     */
    ICON_CAMERA("icon-camera", "\uE90f"),
    /**
     * Headphones icon
     */
    ICON_HEADPHONES("icon-headphones", "\uE910"),
    /**
     * Music icon
     */
    ICON_MUSIC("icon-music", "\uE911"),
    /**
     * Play icon
     */
    ICON_PLAY("icon-play", "\uE912"),
    /**
     * Film icon
     */
    ICON_FILM("icon-film", "\uE913"),
    /**
     * Video camera icon
     */
    ICON_VIDEO_CAMERA("icon-video-camera", "\uE914"),
    /**
     * Dice icon
     */
    ICON_DICE("icon-dice", "\uE915"),
    /**
     * Pacman icon
     */
    ICON_PACMAN("icon-pacman", "\uE916"),
    /**
     * Spades icon
     */
    ICON_SPADES("icon-spades", "\uE917"),
    /**
     * Clubs icon
     */
    ICON_CLUBS("icon-clubs", "\uE918"),
    /**
     * Diamonds icon
     */
    ICON_DIAMONDS("icon-diamonds", "\uE919"),
    /**
     * Bullhorn icon
     */
    ICON_BULLHORN("icon-bullhorn", "\uE91a"),
    /**
     * Connection icon
     */
    ICON_CONNECTION("icon-connection", "\uE91b"),
    /**
     * Podcast icon
     */
    ICON_PODCAST("icon-podcast", "\uE91c"),
    /**
     * Feed icon
     */
    ICON_FEED("icon-feed", "\uE91d"),
    /**
     * Mic icon
     */
    ICON_MIC("icon-mic", "\uE91e"),
    /**
     * Book icon
     */
    ICON_BOOK("icon-book", "\uE91f"),
    /**
     * Books icon
     */
    ICON_BOOKS("icon-books", "\uE920"),
    /**
     * Library icon
     */
    ICON_LIBRARY("icon-library", "\uE921"),
    /**
     * Text file icon
     */
    ICON_FILE_TEXT("icon-file-text", "\uE922"),
    /**
     * Profile icon
     */
    ICON_PROFILE("icon-profile", "\uE923"),
    /**
     * Empty file icon
     */
    ICON_FILE_EMPTY("icon-file-empty", "\uE924"),
    /**
     * Empty files icon
     */
    ICON_FILES_EMPTY("icon-files-empty", "\uE925"),
    /**
     * Text file icon
     */
    ICON_FILE_TEXT2("icon-file-text2", "\uE926"),
    /**
     * Picture file icon
     */
    ICON_FILE_PICTURE("icon-file-picture", "\uE927"),
    /**
     * Music file icon
     */
    ICON_FILE_MUSIC("icon-file-music", "\uE928"),
    /**
     * Play file icon
     */
    ICON_FILE_PLAY("icon-file-play", "\uE929"),
    /**
     * Video file icon
     */
    ICON_FILE_VIDEO("icon-file-video", "\uE92a"),
    /**
     * Zip file icon
     */
    ICON_FILE_ZIP("icon-file-zip", "\uE92b"),
    /**
     * Copy file icon
     */
    ICON_COPY("icon-copy", "\uE92c"),
    /**
     * Paste file icon
     */
    ICON_PASTE("icon-paste", "\uE92d"),
    /**
     * Stack icon
     */
    ICON_STACK("icon-stack", "\uE92e"),
    /**
     * Folder icon
     */
    ICON_FOLDER("icon-folder", "\uE92f"),
    /**
     * Open folder icon
     */
    ICON_FOLDER_OPEN("icon-folder-open", "\uE930"),
    /**
     * Folder '+' icon
     */
    ICON_FOLDER_PLUS("icon-folder-plus", "\uE931"),
    /**
     * Folder '-' icon
     */
    ICON_FOLDER_MINUS("icon-folder-minus", "\uE932"),
    /**
     * Folder download icon
     */
    ICON_FOLDER_DOWNLOAD("icon-folder-download", "\uE933"),
    /**
     * Folder upload icon
     */
    ICON_FOLDER_UPLOAD("icon-folder-upload", "\uE934"),
    /**
     * Price tag icon
     */
    ICON_PRICE_TAG("icon-price-tag", "\uE935"),
    /**
     * Price tags icon
     */
    ICON_PRICE_TAGS("icon-price-tags", "\uE936"),
    /**
     * Barcode icon
     */
    ICON_BARCODE("icon-barcode", "\uE937"),
    /**
     * QR code icon
     */
    ICON_QRCODE("icon-qrcode", "\uE938"),
    /**
     * Ticket icon
     */
    ICON_TICKET("icon-ticket", "\uE939"),
    /**
     * Cart icon
     */
    ICON_CART("icon-cart", "\uE93a"),
    /**
     * Dollar coin icon
     */
    ICON_COIN_DOLLAR("icon-coin-dollar", "\uE93b"),
    /**
     * Euro coin icon
     */
    ICON_COIN_EURO("icon-coin-euro", "\uE93c"),
    /**
     * Pound coin icon
     */
    ICON_COIN_POUND("icon-coin-pound", "\uE93d"),
    /**
     * Yen coin icon
     */
    ICON_COIN_YEN("icon-coin-yen", "\uE93e"),
    /**
     * Credit card icon
     */
    ICON_CREDIT_CARD("icon-credit-card", "\uE93f"),
    /**
     * Calculator icon
     */
    ICON_CALCULATOR("icon-calculator", "\uE940"),
    /**
     * Lifebuoy icon
     */
    ICON_LIFEBUOY("icon-lifebuoy", "\uE941"),
    /**
     * Phone icon
     */
    ICON_PHONE("icon-phone", "\uE942"),
    /**
     * Hangup phone icon
     */
    ICON_PHONE_HANG_UP("icon-phone-hang-up", "\uE943"),
    /**
     * Address book icon
     */
    ICON_ADDRESS_BOOK("icon-address-book", "\uE944"),
    /**
     * Envelope icon
     */
    ICON_ENVELOP("icon-envelop", "\uE945"),
    /**
     * Pushpin icon
     */
    ICON_PUSHPIN("icon-pushpin", "\uE946"),
    /**
     * Location icon
     */
    ICON_LOCATION("icon-location", "\uE947"),
    /**
     * Location icon
     */
    ICON_LOCATION2("icon-location2", "\uE948"),
    /**
     * Compass icon
     */
    ICON_COMPASS("icon-compass", "\uE949"),
    /**
     * Compass icon
     */
    ICON_COMPASS2("icon-compass2", "\uE94a"),
    /**
     * Map icon
     */
    ICON_MAP("icon-map", "\uE94b"),
    /**
     * Map icon
     */
    ICON_MAP2("icon-map2", "\uE94c"),
    /**
     * History icon
     */
    ICON_HISTORY("icon-history", "\uE94d"),
    /**
     * Clock icon
     */
    ICON_CLOCK("icon-clock", "\uE94e"),
    /**
     * Clock icon
     */
    ICON_CLOCK2("icon-clock2", "\uE94f"),
    /**
     * Alarm icon
     */
    ICON_ALARM("icon-alarm", "\uE950"),
    /**
     * Bell icon
     */
    ICON_BELL("icon-bell", "\uE951"),
    /**
     * Stopwatch icon
     */
    ICON_STOPWATCH("icon-stopwatch", "\uE952"),
    /**
     * Calendar icon
     */
    ICON_CALENDAR("icon-calendar", "\uE953"),
    /**
     * Printer icon
     */
    ICON_PRINTER("icon-printer", "\uE954"),
    /**
     * Keyboard icon
     */
    ICON_KEYBOARD("icon-keyboard", "\uE955"),
    /**
     * Display icon
     */
    ICON_DISPLAY("icon-display", "\uE956"),
    /**
     * Laptop icon
     */
    ICON_LAPTOP("icon-laptop", "\uE957"),
    /**
     * Mobile icon
     */
    ICON_MOBILE("icon-mobile", "\uE958"),
    /**
     * Mobile icon
     */
    ICON_MOBILE2("icon-mobile2", "\uE959"),
    /**
     * Tablet icon
     */
    ICON_TABLET("icon-tablet", "\uE95a"),
    /**
     * TV icon
     */
    ICON_TV("icon-tv", "\uE95b"),
    /**
     * Drawer icon
     */
    ICON_DRAWER("icon-drawer", "\uE95c"),
    /**
     * Drawer icon
     */
    ICON_DRAWER2("icon-drawer2", "\uE95d"),
    /**
     * Box add icon
     */
    ICON_BOX_ADD("icon-box-add", "\uE95e"),
    /**
     * Box remove icon
     */
    ICON_BOX_REMOVE("icon-box-remove", "\uE95f"),
    /**
     * Download icon
     */
    ICON_DOWNLOAD("icon-download", "\uE960"),
    /**
     * Upload icon
     */
    ICON_UPLOAD("icon-upload", "\uE961"),
    /**
     * Floppy disk icon
     */
    ICON_FLOPPY_DISK("icon-floppy-disk", "\uE962"),
    /**
     * Drive icon
     */
    ICON_DRIVE("icon-drive", "\uE963"),
    /**
     * Dataset icon
     */
    ICON_DATABASE("icon-database", "\uE964"),
    /**
     * Undo icon
     */
    ICON_UNDO("icon-undo", "\uE965"),
    /**
     * Redo icon
     */
    ICON_REDO("icon-redo", "\uE966"),
    /**
     * Undo icon
     */
    ICON_UNDO2("icon-undo2", "\uE967"),
    /**
     * Redo icon
     */
    ICON_REDO2("icon-redo2", "\uE968"),
    /**
     * Forward icon
     */
    ICON_FORWARD("icon-forward", "\uE969"),
    /**
     * Reply icon
     */
    ICON_REPLY("icon-reply", "\uE96a"),
    /**
     * Bubble icon
     */
    ICON_BUBBLE("icon-bubble", "\uE96b"),
    /**
     * Bubble icon
     */
    ICON_BUBBLES("icon-bubbles", "\uE96c"),
    /**
     * Bubbles icon
     */
    ICON_BUBBLES2("icon-bubbles2", "\uE96d"),
    /**
     * Bubbles icon
     */
    ICON_BUBBLE2("icon-bubble2", "\uE96e"),
    /**
     * Bubbles icon
     */
    ICON_BUBBLES3("icon-bubbles3", "\uE96f"),
    /**
     * Bubbles icon
     */
    ICON_BUBBLES4("icon-bubbles4", "\uE970"),
    /**
     * User icon
     */
    ICON_USER("icon-user", "\uE971"),
    /**
     * Users icon
     */
    ICON_USERS("icon-users", "\uE972"),
    /**
     * Add user icon
     */
    ICON_USER_PLUS("icon-user-plus", "\uE973"),
    /**
     * Remove user icon
     */
    ICON_USER_MINUS("icon-user-minus", "\uE974"),
    /**
     * Check user icon
     */
    ICON_USER_CHECK("icon-user-check", "\uE975"),
    /**
     * Tie user icon
     */
    ICON_USER_TIE("icon-user-tie", "\uE976"),
    /**
     * Left quotes icon
     */
    ICON_QUOTES_LEFT("icon-quotes-left", "\uE977"),
    /**
     * Right quotes icon
     */
    ICON_QUOTES_RIGHT("icon-quotes-right", "\uE978"),
    /**
     * Hourglass icon
     */
    ICON_HOUR_GLASS("icon-hour-glass", "\uE979"),
    /**
     * Spinner icon
     */
    ICON_SPINNER("icon-spinner", "\uE97a"),
    /**
     * Spinner icon
     */
    ICON_SPINNER2("icon-spinner2", "\uE97b"),
    /**
     * Spinner icon
     */
    ICON_SPINNER3("icon-spinner3", "\uE97c"),
    /**
     * Spinner icon
     */
    ICON_SPINNER4("icon-spinner4", "\uE97d"),
    /**
     * Spinner icon
     */
    ICON_SPINNER5("icon-spinner5", "\uE97e"),
    /**
     * Spinner icon
     */
    ICON_SPINNER6("icon-spinner6", "\uE97f"),
    /**
     * Spinner icon
     */
    ICON_SPINNER7("icon-spinner7", "\uE980"),
    /**
     * Spinner icon
     */
    ICON_SPINNER8("icon-spinner8", "\uE981"),
    /**
     * Spinner icon
     */
    ICON_SPINNER9("icon-spinner9", "\uE982"),
    /**
     * Spinner icon
     */
    ICON_SPINNER10("icon-spinner10", "\uE983"),
    /**
     * Spinner icon
     */
    ICON_SPINNER11("icon-spinner11", "\uE984"),
    /**
     * Binoculars icon
     */
    ICON_BINOCULARS("icon-binoculars", "\uE985"),
    /**
     * Search icon
     */
    ICON_SEARCH("icon-search", "\uE986"),
    /**
     * Zoom in icon
     */
    ICON_ZOOM_IN("icon-zoom-in", "\uE987"),
    /**
     * Zoom out icon
     */
    ICON_ZOOM_OUT("icon-zoom-out", "\uE988"),
    /**
     * Enlarge icon
     */
    ICON_ENLARGE("icon-enlarge", "\uE989"),
    /**
     * Shrink icon
     */
    ICON_SHRINK("icon-shrink", "\uE98a"),
    /**
     * Enlarge icon
     */
    ICON_ENLARGE2("icon-enlarge2", "\uE98b"),
    /**
     * Shrink icon
     */
    ICON_SHRINK2("icon-shrink2", "\uE98c"),
    /**
     * Key icon
     */
    ICON_KEY("icon-key", "\uE98d"),
    /**
     * Key icon
     */
    ICON_KEY2("icon-key2", "\uE98e"),
    /**
     * Lock icon
     */
    ICON_LOCK("icon-lock", "\uE98f"),
    /**
     * Unlocked icon
     */
    ICON_UNLOCKED("icon-unlocked", "\uE990"),
    /**
     * Wrench icon
     */
    ICON_WRENCH("icon-wrench", "\uE991"),
    /**
     * Equalizer icon
     */
    ICON_EQUALIZER("icon-equalizer", "\uE992"),
    /**
     * Equalizer icon
     */
    ICON_EQUALIZER2("icon-equalizer2", "\uE993"),
    /**
     * Cog icon
     */
    ICON_COG("icon-cog", "\uE994"),
    /**
     * Cogs icon
     */
    ICON_COGS("icon-cogs", "\uE995"),
    /**
     * Hammer icon
     */
    ICON_HAMMER("icon-hammer", "\uE996"),
    /**
     * Magic wand icon
     */
    ICON_MAGIC_WAND("icon-magic-wand", "\uE997"),
    /**
     * Aid kit icon
     */
    ICON_AID_KIT("icon-aid-kit", "\uE998"),
    /**
     * Bug icon
     */
    ICON_BUG("icon-bug", "\uE999"),
    /**
     * Pie chart icon
     */
    ICON_PIE_CHART("icon-pie-chart", "\uE99a"),
    /**
     * Stats dots icon
     */
    ICON_STATS_DOTS("icon-stats-dots", "\uE99b"),
    /**
     * Stats bars icon
     */
    ICON_STATS_BARS("icon-stats-bars", "\uE99c"),
    /**
     * Stats bars icon
     */
    ICON_STATS_BARS2("icon-stats-bars2", "\uE99d"),
    /**
     * Trophy icon
     */
    ICON_TROPHY("icon-trophy", "\uE99e"),
    /**
     * Gift icon
     */
    ICON_GIFT("icon-gift", "\uE99f"),
    /**
     * Glass icon
     */
    ICON_GLASS("icon-glass", "\uE9a0"),
    /**
     * Glass icon
     */
    ICON_GLASS2("icon-glass2", "\uE9a1"),
    /**
     * Mug icon
     */
    ICON_MUG("icon-mug", "\uE9a2"),
    /**
     * Fork and spoon icon
     */
    ICON_SPOON_KNIFE("icon-spoon-knife", "\uE9a3"),
    /**
     * Leaf icon
     */
    ICON_LEAF("icon-leaf", "\uE9a4"),
    /**
     * Rocket icon
     */
    ICON_ROCKET("icon-rocket", "\uE9a5"),
    /**
     * Meter icon
     */
    ICON_METER("icon-meter", "\uE9a6"),
    /**
     * Meter icon
     */
    ICON_METER2("icon-meter2", "\uE9a7"),
    /**
     * Hammer icon
     */
    ICON_HAMMER2("icon-hammer2", "\uE9a8"),
    /**
     * Fire icon
     */
    ICON_FIRE("icon-fire", "\uE9a9"),
    /**
     * Lab icon
     */
    ICON_LAB("icon-lab", "\uE9aa"),
    /**
     * Magnet icon
     */
    ICON_MAGNET("icon-magnet", "\uE9ab"),
    /**
     * Bin icon
     */
    ICON_BIN("icon-bin", "\uE9ac"),
    /**
     * Bin icon
     */
    ICON_BIN2("icon-bin2", "\uE9ad"),
    /**
     * Briefcase icon
     */
    ICON_BRIEFCASE("icon-briefcase", "\uE9ae"),
    /**
     * Airplane icon
     */
    ICON_AIRPLANE("icon-airplane", "\uE9af"),
    /**
     * Truck icon
     */
    ICON_TRUCK("icon-truck", "\uE9b0"),
    /**
     * Road icon
     */
    ICON_ROAD("icon-road", "\uE9b1"),
    /**
     * Accessibility icon
     */
    ICON_ACCESSIBILITY("icon-accessibility", "\uE9b2"),
    /**
     * Target icon
     */
    ICON_TARGET("icon-target", "\uE9b3"),
    /**
     * Shield icon
     */
    ICON_SHIELD("icon-shield", "\uE9b4"),
    /**
     * Power icon
     */
    ICON_POWER("icon-power", "\uE9b5"),
    /**
     * Switch icon
     */
    ICON_SWITCH("icon-switch", "\uE9b6"),
    /**
     * Power cord icon
     */
    ICON_POWER_CORD("icon-power-cord", "\uE9b7"),
    /**
     * Clipboard icon
     */
    ICON_CLIPBOARD("icon-clipboard", "\uE9b8"),
    /**
     * Numbered list icon
     */
    ICON_LIST_NUMBERED("icon-list-numbered", "\uE9b9"),
    /**
     * List icon
     */
    ICON_LIST("icon-list", "\uE9ba"),
    /**
     * List icon
     */
    ICON_LIST2("icon-list2", "\uE9bb"),
    /**
     * Tree icon
     */
    ICON_TREE("icon-tree", "\uE9bc"),
    /**
     * Menu icon
     */
    ICON_MENU("icon-menu", "\uE9bd"),
    /**
     * Menu icon
     */
    ICON_MENU2("icon-menu2", "\uE9be"),
    /**
     * Menu icon
     */
    ICON_MENU3("icon-menu3", "\uE9bf"),
    /**
     * Menu icon
     */
    ICON_MENU4("icon-menu4", "\uE9c0"),
    /**
     * Cloud icon
     */
    ICON_CLOUD("icon-cloud", "\uE9c1"),
    /**
     * Cloud download icon
     */
    ICON_CLOUD_DOWNLOAD("icon-cloud-download", "\uE9c2"),
    /**
     * Cloud upload icon
     */
    ICON_CLOUD_UPLOAD("icon-cloud-upload", "\uE9c3"),
    /**
     * Cloud with check icon
     */
    ICON_CLOUD_CHECK("icon-cloud-check", "\uE9c4"),
    /**
     * Download icon
     */
    ICON_DOWNLOAD2("icon-download2", "\uE9c5"),
    /**
     * Upload icon
     */
    ICON_UPLOAD2("icon-upload2", "\uE9c6"),
    /**
     * Download icon
     */
    ICON_DOWNLOAD3("icon-download3", "\uE9c7"),
    /**
     * Upload icon
     */
    ICON_UPLOAD3("icon-upload3", "\uE9c8"),
    /**
     * Sphere icon
     */
    ICON_SPHERE("icon-sphere", "\uE9c9"),
    /**
     * Earth icon
     */
    ICON_EARTH("icon-earth", "\uE9ca"),
    /**
     * Link icon
     */
    ICON_LINK("icon-link", "\uE9cb"),
    /**
     * Flag icon
     */
    ICON_FLAG("icon-flag", "\uE9cc"),
    /**
     * Attachment icon
     */
    ICON_ATTACHMENT("icon-attachment", "\uE9cd"),
    /**
     * Eye icon
     */
    ICON_EYE("icon-eye", "\uE9ce"),
    /**
     * Plus eye icon
     */
    ICON_EYE_PLUS("icon-eye-plus", "\uE9cf"),
    /**
     * Minus eye icon
     */
    ICON_EYE_MINUS("icon-eye-minus", "\uE9d0"),
    /**
     * Struck-out eye icon
     */
    ICON_EYE_BLOCKED("icon-eye-blocked", "\uE9d1"),
    /**
     * Bookmark icon
     */
    ICON_BOOKMARK("icon-bookmark", "\uE9d2"),
    /**
     * Bookmarks icon
     */
    ICON_BOOKMARKS("icon-bookmarks", "\uE9d3"),
    /**
     * Sun icon
     */
    ICON_SUN("icon-sun", "\uE9d4"),
    /**
     * Contrast icon
     */
    ICON_CONTRAST("icon-contrast", "\uE9d5"),
    /**
     * Brightness-contrast icon
     */
    ICON_BRIGHTNESS_CONTRAST("icon-brightness-contrast", "\uE9d6"),
    /**
     * Empty star icon
     */
    ICON_STAR_EMPTY("icon-star-empty", "\uE9d7"),
    /**
     * Half star icon
     */
    ICON_STAR_HALF("icon-star-half", "\uE9d8"),
    /**
     * Full star icon
     */
    ICON_STAR_FULL("icon-star-full", "\uE9d9"),
    /**
     * Heart icon
     */
    ICON_HEART("icon-heart", "\uE9da"),
    /**
     * Broken heart icon
     */
    ICON_HEART_BROKEN("icon-heart-broken", "\uE9db"),
    /**
     * Man icon
     */
    ICON_MAN("icon-man", "\uE9dc"),
    /**
     * Woman icon
     */
    ICON_WOMAN("icon-woman", "\uE9dd"),
    /**
     * Man and woman icon
     */
    ICON_MAN_WOMAN("icon-man-woman", "\uE9de"),
    /**
     * Happy icon
     */
    ICON_HAPPY("icon-happy", "\uE9df"),
    /**
     * Happy icon
     */
    ICON_HAPPY2("icon-happy2", "\uE9e0"),
    /**
     * Smile icon
     */
    ICON_SMILE("icon-smile", "\uE9e1"),
    /**
     * Smile icon
     */
    ICON_SMILE2("icon-smile2", "\uE9e2"),
    /**
     * Tongue icon
     */
    ICON_TONGUE("icon-tongue", "\uE9e3"),
    /**
     * Tongue icon
     */
    ICON_TONGUE2("icon-tongue2", "\uE9e4"),
    /**
     * Sad icon
     */
    ICON_SAD("icon-sad", "\uE9e5"),
    /**
     * Sad icon
     */
    ICON_SAD2("icon-sad2", "\uE9e6"),
    /**
     * Wink icon
     */
    ICON_WINK("icon-wink", "\uE9e7"),
    /**
     * Wink icon
     */
    ICON_WINK2("icon-wink2", "\uE9e8"),
    /**
     * Grin icon
     */
    ICON_GRIN("icon-grin", "\uE9e9"),
    /**
     * Grin icon
     */
    ICON_GRIN2("icon-grin2", "\uE9ea"),
    /**
     * Cool icon
     */
    ICON_COOL("icon-cool", "\uE9eb"),
    /**
     * Cool icon
     */
    ICON_COOL2("icon-cool2", "\uE9ec"),
    /**
     * Angry icon
     */
    ICON_ANGRY("icon-angry", "\uE9ed"),
    /**
     * Angry icon
     */
    ICON_ANGRY2("icon-angry2", "\uE9ee"),
    /**
     * Evil icon
     */
    ICON_EVIL("icon-evil", "\uE9ef"),
    /**
     * Evil icon
     */
    ICON_EVIL2("icon-evil2", "\uE9f0"),
    /**
     * Shocked icon
     */
    ICON_SHOCKED("icon-shocked", "\uE9f1"),
    /**
     * Shocked icon
     */
    ICON_SHOCKED2("icon-shocked2", "\uE9f2"),
    /**
     * Baffled icon
     */
    ICON_BAFFLED("icon-baffled", "\uE9f3"),
    /**
     * Baffled icon
     */
    ICON_BAFFLED2("icon-baffled2", "\uE9f4"),
    /**
     * Confused icon
     */
    ICON_CONFUSED("icon-confused", "\uE9f5"),
    /**
     * Confused icon
     */
    ICON_CONFUSED2("icon-confused2", "\uE9f6"),
    /**
     * Neutral icon
     */
    ICON_NEUTRAL("icon-neutral", "\uE9f7"),
    /**
     * Neutral icon
     */
    ICON_NEUTRAL2("icon-neutral2", "\uE9f8"),
    /**
     * Hipster icon
     */
    ICON_HIPSTER("icon-hipster", "\uE9f9"),
    /**
     * Hipster icon
     */
    ICON_HIPSTER2("icon-hipster2", "\uE9fa"),
    /**
     * Wondering icon
     */
    ICON_WONDERING("icon-wondering", "\uE9fb"),
    /**
     * Wondering icon
     */
    ICON_WONDERING2("icon-wondering2", "\uE9fc"),
    /**
     * Sleepy icon
     */
    ICON_SLEEPY("icon-sleepy", "\uE9fd"),
    /**
     * Sleepy icon
     */
    ICON_SLEEPY2("icon-sleepy2", "\uE9fe"),
    /**
     * Frustrated icon
     */
    ICON_FRUSTRATED("icon-frustrated", "\uE9ff"),
    /**
     * Frustrated icon
     */
    ICON_FRUSTRATED2("icon-frustrated2", "\uEa00"),
    /**
     * Crying icon
     */
    ICON_CRYING("icon-crying", "\uEa01"),
    /**
     * Crying icon
     */
    ICON_CRYING2("icon-crying2", "\uEa02"),
    /**
     * Point up icon
     */
    ICON_POINT_UP("icon-point-up", "\uEa03"),
    /**
     * Point right icon
     */
    ICON_POINT_RIGHT("icon-point-right", "\uEa04"),
    /**
     * Point down icon
     */
    ICON_POINT_DOWN("icon-point-down", "\uEa05"),
    /**
     * Point left icon
     */
    ICON_POINT_LEFT("icon-point-left", "\uEa06"),
    /**
     * Warning icon
     */
    ICON_WARNING("icon-warning", "\uEa07"),
    /**
     * Notification icon
     */
    ICON_NOTIFICATION("icon-notification", "\uEa08"),
    /**
     * Question icon
     */
    ICON_QUESTION("icon-question", "\uEa09"),
    /**
     * Plus icon
     */
    ICON_PLUS("icon-plus", "\uEa0a"),
    /**
     * Minus icon
     */
    ICON_MINUS("icon-minus", "\uEa0b"),
    /**
     * Info icon
     */
    ICON_INFO("icon-info", "\uEa0c"),
    /**
     * Circle cancel icon
     */
    ICON_CANCEL_CIRCLE("icon-cancel-circle", "\uEa0d"),
    /**
     * Blocked icon
     */
    ICON_BLOCKED("icon-blocked", "\uEa0e"),
    /**
     * Cross icon
     */
    ICON_CROSS("icon-cross", "\uEa0f"),
    /**
     * Checkmark icon
     */
    ICON_CHECKMARK("icon-checkmark", "\uEa10"),
    /**
     * Checkmark icon
     */
    ICON_CHECKMARK2("icon-checkmark2", "\uEa11"),
    /**
     * Spell check icon
     */
    ICON_SPELL_CHECK("icon-spell-check", "\uEa12"),
    /**
     * Enter icon
     */
    ICON_ENTER("icon-enter", "\uEa13"),
    /**
     * Exit icon
     */
    ICON_EXIT("icon-exit", "\uEa14"),
    /**
     * PLay icon
     */
    ICON_PLAY2("icon-play2", "\uEa15"),
    /**
     * Pause icon
     */
    ICON_PAUSE("icon-pause", "\uEa16"),
    /**
     * Stop icon
     */
    ICON_STOP("icon-stop", "\uEa17"),
    /**
     * Previous icon
     */
    ICON_PREVIOUS("icon-previous", "\uEa18"),
    /**
     * Next icon
     */
    ICON_NEXT("icon-next", "\uEa19"),
    /**
     * Backward icon
     */
    ICON_BACKWARD("icon-backward", "\uEa1a"),
    /**
     * Forward icon
     */
    ICON_FORWARD2("icon-forward2", "\uEa1b"),
    /**
     * Play icon
     */
    ICON_PLAY3("icon-play3", "\uEa1c"),
    /**
     * Pause icon
     */
    ICON_PAUSE2("icon-pause2", "\uEa1d"),
    /**
     * Stop icon
     */
    ICON_STOP2("icon-stop2", "\uEa1e"),
    /**
     * Backward icon
     */
    ICON_BACKWARD2("icon-backward2", "\uEa1f"),
    /**
     * Forward icon
     */
    ICON_FORWARD3("icon-forward3", "\uEa20"),
    /**
     * First icon
     */
    ICON_FIRST("icon-first", "\uEa21"),
    /**
     * Last icon
     */
    ICON_LAST("icon-last", "\uEa22"),
    /**
     * Previous icon
     */
    ICON_PREVIOUS2("icon-previous2", "\uEa23"),
    /**
     * Next icon
     */
    ICON_NEXT2("icon-next2", "\uEa24"),
    /**
     * Eject icon
     */
    ICON_EJECT("icon-eject", "\uEa25"),
    /**
     * High volume icon
     */
    ICON_VOLUME_HIGH("icon-volume-high", "\uEa26"),
    /**
     * Medium volume icon
     */
    ICON_VOLUME_MEDIUM("icon-volume-medium", "\uEa27"),
    /**
     * Low volume icon
     */
    ICON_VOLUME_LOW("icon-volume-low", "\uEa28"),
    /**
     * Mute volume icon
     */
    ICON_VOLUME_MUTE("icon-volume-mute", "\uEa29"),
    /**
     * Mute volume icon
     */
    ICON_VOLUME_MUTE2("icon-volume-mute2", "\uEa2a"),
    /**
     * Increase volume icon
     */
    ICON_VOLUME_INCREASE("icon-volume-increase", "\uEa2b"),
    /**
     * Decrease volume icon
     */
    ICON_VOLUME_DECREASE("icon-volume-decrease", "\uEa2c"),
    /**
     * Loop icon
     */
    ICON_LOOP("icon-loop", "\uEa2d"),
    /**
     * Loop icon
     */
    ICON_LOOP2("icon-loop2", "\uEa2e"),
    /**
     * Infinite icon
     */
    ICON_INFINITE("icon-infinite", "\uEa2f"),
    /**
     * Shuffle icon
     */
    ICON_SHUFFLE("icon-shuffle", "\uEa30"),
    /**
     * Up-left arrow icon
     */
    ICON_ARROW_UP_LEFT("icon-arrow-up-left", "\uEa31"),
    /**
     * Up arrow icon
     */
    ICON_ARROW_UP("icon-arrow-up", "\uEa32"),
    /**
     * Up-right arrow icon
     */
    ICON_ARROW_UP_RIGHT("icon-arrow-up-right", "\uEa33"),
    /**
     * Right arrow icon
     */
    ICON_ARROW_RIGHT("icon-arrow-right", "\uEa34"),
    /**
     * Down-right arrow icon
     */
    ICON_ARROW_DOWN_RIGHT("icon-arrow-down-right", "\uEa35"),
    /**
     * Down arrow icon
     */
    ICON_ARROW_DOWN("icon-arrow-down", "\uEa36"),
    /**
     * Down-left arrow icon
     */
    ICON_ARROW_DOWN_LEFT("icon-arrow-down-left", "\uEa37"),
    /**
     * Left arrow icon
     */
    ICON_ARROW_LEFT("icon-arrow-left", "\uEa38"),
    /**
     * Up left arrow icon
     */
    ICON_ARROW_UP_LEFT2("icon-arrow-up-left2", "\uEa39"),
    /**
     * Up arrow icon
     */
    ICON_ARROW_UP2("icon-arrow-up2", "\uEa3a"),
    /**
     * Up-right arrow icon
     */
    ICON_ARROW_UP_RIGHT2("icon-arrow-up-right2", "\uEa3b"),
    /**
     * Right arrow icon
     */
    ICON_ARROW_RIGHT2("icon-arrow-right2", "\uEa3c"),
    /**
     * Down-right arrow icon
     */
    ICON_ARROW_DOWN_RIGHT2("icon-arrow-down-right2", "\uEa3d"),
    /**
     * Down arrow icon
     */
    ICON_ARROW_DOWN2("icon-arrow-down2", "\uEa3e"),
    /**
     * Down-left arrow icon
     */
    ICON_ARROW_DOWN_LEFT2("icon-arrow-down-left2", "\uEa3f"),
    /**
     * Left arrow icon
     */
    ICON_ARROW_LEFT2("icon-arrow-left2", "\uEa40"),
    /**
     * Circle up arrow icon
     */
    ICON_CIRCLE_UP("icon-circle-up", "\uEa41"),
    /**
     * Circle right arrow icon
     */
    ICON_CIRCLE_RIGHT("icon-circle-right", "\uEa42"),
    /**
     * Circle down arrow icon
     */
    ICON_CIRCLE_DOWN("icon-circle-down", "\uEa43"),
    /**
     * Circle left arrow icon
     */
    ICON_CIRCLE_LEFT("icon-circle-left", "\uEa44"),
    /**
     * Tab icon
     */
    ICON_TAB("icon-tab", "\uEa45"),
    /**
     * Move up icon
     */
    ICON_MOVE_UP("icon-move-up", "\uEa46"),
    /**
     * Move down icon
     */
    ICON_MOVE_DOWN("icon-move-down", "\uEa47"),
    /**
     * Sort alphabetic ascending
     */
    ICON_SORT_ALPHA_ASC("icon-sort-alpha-asc", "\uEa48"),
    /**
     * Sort alphabetical descending
     */
    ICON_SORT_ALPHA_DESC("icon-sort-alpha-desc", "\uEa49"),
    /**
     * Sort numeric ascending
     */
    ICON_SORT_NUMERIC_ASC("icon-sort-numeric-asc", "\uEa4a"),
    /**
     * Sort numeric descending
     */
    ICON_SORT_NUMBERIC_DESC("icon-sort-numberic-desc", "\uEa4b"),
    /**
     * Sort amount ascending
     */
    ICON_SORT_AMOUNT_ASC("icon-sort-amount-asc", "\uEa4c"),
    /**
     * Sort amount descending
     */
    ICON_SORT_AMOUNT_DESC("icon-sort-amount-desc", "\uEa4d"),
    /**
     * Command icon
     */
    ICON_COMMAND("icon-command", "\uEa4e"),
    /**
     * Shift icon
     */
    ICON_SHIFT("icon-shift", "\uEa4f"),
    /**
     * Ctrl icon
     */
    ICON_CTRL("icon-ctrl", "\uEa50"),
    /**
     * Opt icon
     */
    ICON_OPT("icon-opt", "\uEa51"),
    /**
     * Checked checkbox icon
     */
    ICON_CHECKBOX_CHECKED("icon-checkbox-checked", "\uEa52"),
    /**
     * Unchecked checkbox icon
     */
    ICON_CHECKBOX_UNCHECKED("icon-checkbox-unchecked", "\uEa53"),
    /**
     * Checked radio icon
     */
    ICON_RADIO_CHECKED("icon-radio-checked", "\uEa54"),
    /**
     * Checked radio icon
     */
    ICON_RADIO_CHECKED2("icon-radio-checked2", "\uEa55"),
    /**
     * Unchecked radio icon
     */
    ICON_RADIO_UNCHECKED("icon-radio-unchecked", "\uEa56"),
    /**
     * Crop icon
     */
    ICON_CROP("icon-crop", "\uEa57"),
    /**
     * Make group icon
     */
    ICON_MAKE_GROUP("icon-make-group", "\uEa58"),
    /**
     * Ungroup icon
     */
    ICON_UNGROUP("icon-ungroup", "\uEa59"),
    /**
     * Scissors icon
     */
    ICON_SCISSORS("icon-scissors", "\uEa5a"),
    /**
     * Filter icon
     */
    ICON_FILTER("icon-filter", "\uEa5b"),
    /**
     * Font icon
     */
    ICON_FONT("icon-font", "\uEa5c"),
    /**
     * Ligature icon
     */
    ICON_LIGATURE("icon-ligature", "\uEa5d"),
    /**
     * Ligature icon
     */
    ICON_LIGATURE2("icon-ligature2", "\uEa5e"),
    /**
     * Text height icon
     */
    ICON_TEXT_HEIGHT("icon-text-height", "\uEa5f"),
    /**
     * Text width icon
     */
    ICON_TEXT_WIDTH("icon-text-width", "\uEa60"),
    /**
     * Font size icon
     */
    ICON_FONT_SIZE("icon-font-size", "\uEa61"),
    /**
     * Bold icon
     */
    ICON_BOLD("icon-bold", "\uEa62"),
    /**
     * Underline icon
     */
    ICON_UNDERLINE("icon-underline", "\uEa63"),
    /**
     * Italic icon
     */
    ICON_ITALIC("icon-italic", "\uEa64"),
    /**
     * Strike-through icon
     */
    ICON_STRIKETHROUGH("icon-strikethrough", "\uEa65"),
    /**
     * Omega icon
     */
    ICON_OMEGA("icon-omega", "\uEa66"),
    /**
     * Sigma icon
     */
    ICON_SIGMA("icon-sigma", "\uEa67"),
    /**
     * Page break icon
     */
    ICON_PAGE_BREAK("icon-page-break", "\uEa68"),
    /**
     * Superscript icon
     */
    ICON_SUPERSCRIPT("icon-superscript", "\uEa69"),
    /**
     * Subscript icon
     */
    ICON_SUBSCRIPT("icon-subscript", "\uEa6a"),
    /**
     * Superscript icon
     */
    ICON_SUPERSCRIPT2("icon-superscript2", "\uEa6b"),
    /**
     * Subscript icon
     */
    ICON_SUBSCRIPT2("icon-subscript2", "\uEa6c"),
    /**
     * Color icon
     */
    ICON_TEXT_COLOR("icon-text-color", "\uEa6d"),
    /**
     * Pagebreak icon
     */
    ICON_PAGEBREAK("icon-pagebreak", "\uEa6e"),
    /**
     * Clear formatting icon
     */
    ICON_CLEAR_FORMATTING("icon-clear-formatting", "\uEa6f"),
    /**
     * Table icon
     */
    ICON_TABLE("icon-table", "\uEa70"),
    /**
     * Table icon
     */
    ICON_TABLE2("icon-table2", "\uEa71"),
    /**
     * Insert template icon
     */
    ICON_INSERT_TEMPLATE("icon-insert-template", "\uEa72"),
    /**
     * Pilcrow icon
     */
    ICON_PILCROW("icon-pilcrow", "\uEa73"),
    /**
     * LTR icon
     */
    ICON_LTR("icon-ltr", "\uEa74"),
    /**
     * RTL icon
     */
    ICON_RTL("icon-rtl", "\uEa75"),
    /**
     * Section icon
     */
    ICON_SECTION("icon-section", "\uEa76"),
    /**
     * Left-align paragraph icon
     */
    ICON_PARAGRAPH_LEFT("icon-paragraph-left", "\uEa77"),
    /**
     * Center-align paragraph icon
     */
    ICON_PARAGRAPH_CENTER("icon-paragraph-center", "\uEa78"),
    /**
     * Right-align paragraph icon
     */
    ICON_PARAGRAPH_RIGHT("icon-paragraph-right", "\uEa79"),
    /**
     * Justify paragraph icon
     */
    ICON_PARAGRAPH_JUSTIFY("icon-paragraph-justify", "\uEa7a"),
    /**
     * Increase indent icon
     */
    ICON_INDENT_INCREASE("icon-indent-increase", "\uEa7b"),
    /**
     * Decrease indent icon
     */
    ICON_INDENT_DECREASE("icon-indent-decrease", "\uEa7c"),
    /**
     * Share icon
     */
    ICON_SHARE("icon-share", "\uEa7d"),
    /**
     * New tab icon
     */
    ICON_NEW_TAB("icon-new-tab", "\uEa7e"),
    /**
     * Embed icon
     */
    ICON_EMBED("icon-embed", "\uEa7f"),
    /**
     * Embed icon
     */
    ICON_EMBED2("icon-embed2", "\uEa80"),
    /**
     * Terminal iconm
     */
    ICON_TERMINAL("icon-terminal", "\uEa81"),
    /**
     * Share icon
     */
    ICON_SHARE2("icon-share2", "\uEa82"),
    /**
     * Mail icon
     */
    ICON_MAIL("icon-mail", "\uEa83"),
    /**
     * Mail icon
     */
    ICON_MAIL2("icon-mail2", "\uEa84"),
    /**
     * Mail icon
     */
    ICON_MAIL3("icon-mail3", "\uEa85"),
    /**
     * Mail icon
     */
    ICON_MAIL4("icon-mail4", "\uEa86"),
    /**
     * Amazon icon
     */
    ICON_AMAZON("icon-amazon", "\uEa87"),
    /**
     * Google icon
     */
    ICON_GOOGLE("icon-google", "\uEa88"),
    /**
     * Google icon
     */
    ICON_GOOGLE2("icon-google2", "\uEa89"),
    /**
     * Google icon
     */
    ICON_GOOGLE3("icon-google3", "\uEa8a"),
    /**
     * Google Plus icon
     */
    ICON_GOOGLE_PLUS("icon-google-plus", "\uEa8b"),
    /**
     * Google Plus icon
     */
    ICON_GOOGLE_PLUS2("icon-google-plus2", "\uEa8c"),
    /**
     * Google Plus icon
     */
    ICON_GOOGLE_PLUS3("icon-google-plus3", "\uEa8d"),
    /**
     * Hangouts icon
     */
    ICON_HANGOUTS("icon-hangouts", "\uEa8e"),
    /**
     * Google Drive icon
     */
    ICON_GOOGLE_DRIVE("icon-google-drive", "\uEa8f"),
    /**
     * Facebook icon
     */
    ICON_FACEBOOK("icon-facebook", "\uEa90"),
    /**
     * Facebook icon
     */
    ICON_FACEBOOK2("icon-facebook2", "\uEa91"),
    /**
     * Instagram icon
     */
    ICON_INSTAGRAM("icon-instagram", "\uEa92"),
    /**
     * Whatsapp icon
     */
    ICON_WHATSAPP("icon-whatsapp", "\uEa93"),
    /**
     * Spotify icon
     */
    ICON_SPOTIFY("icon-spotify", "\uEa94"),
    /**
     * Telegram icon
     */
    ICON_TELEGRAM("icon-telegram", "\uEa95"),
    /**
     * Twitter icon
     */
    ICON_TWITTER("icon-twitter", "\uEa96"),
    /**
     * Vine icon
     */
    ICON_VINE("icon-vine", "\uEa97"),
    /**
     * VK icon
     */
    ICON_VK("icon-vk", "\uEa98"),
    /**
     * Renren icon
     */
    ICON_RENREN("icon-renren", "\uEa99"),
    /**
     * Sina Weibo icon
     */
    ICON_SINA_WEIBO("icon-sina-weibo", "\uEa9a"),
    /**
     * RSS icon
     */
    ICON_RSS("icon-rss", "\uEa9b"),
    /**
     * RSS icon
     */
    ICON_RSS2("icon-rss2", "\uEa9c"),
    /**
     * YouTube icon
     */
    ICON_YOUTUBE("icon-youtube", "\uEa9d"),
    /**
     * YouTube icon
     */
    ICON_YOUTUBE2("icon-youtube2", "\uEa9e"),
    /**
     * Twitch icon
     */
    ICON_TWITCH("icon-twitch", "\uEa9f"),
    /**
     * Vimeo icon
     */
    ICON_VIMEO("icon-vimeo", "\uEaa0"),
    /**
     * Vimeo icon
     */
    ICON_VIMEO2("icon-vimeo2", "\uEaa1"),
    /**
     * Lanyrd icon
     */
    ICON_LANYRD("icon-lanyrd", "\uEaa2"),
    /**
     * Flickr icon
     */
    ICON_FLICKR("icon-flickr", "\uEaa3"),
    /**
     * Flickr icon
     */
    ICON_FLICKR2("icon-flickr2", "\uEaa4"),
    /**
     * Flickr icon
     */
    ICON_FLICKR3("icon-flickr3", "\uEaa5"),
    /**
     * Flickr icon
     */
    ICON_FLICKR4("icon-flickr4", "\uEaa6"),
    /**
     * Dribble icon
     */
    ICON_DRIBBBLE("icon-dribbble", "\uEaa7"),
    /**
     * Behance icon
     */
    ICON_BEHANCE("icon-behance", "\uEaa8"),
    /**
     * Behance icon
     */
    ICON_BEHANCE2("icon-behance2", "\uEaa9"),
    /**
     * Deviant Art icon
     */
    ICON_DEVIANTART("icon-deviantart", "\uEaaa"),
    /**
     * 500px icon
     */
    ICON_500PX("icon-500px", "\uEaab"),
    /**
     * Steam icon
     */
    ICON_STEAM("icon-steam", "\uEaac"),
    /**
     * Steam icon
     */
    ICON_STEAM2("icon-steam2", "\uEaad"),
    /**
     * DropBox icon
     */
    ICON_DROPBOX("icon-dropbox", "\uEaae"),
    /**
     * OneDrive icon
     */
    ICON_ONEDRIVE("icon-onedrive", "\uEaaf"),
    /**
     * GitHub icon
     */
    ICON_GITHUB("icon-github", "\uEab0"),
    /**
     * NPM icon
     */
    ICON_NPM("icon-npm", "\uEab1"),
    /**
     * Base camp icon
     */
    ICON_BASECAMP("icon-basecamp", "\uEab2"),
    /**
     * Trello icon
     */
    ICON_TRELLO("icon-trello", "\uEab3"),
    /**
     * Wordpress icon
     */
    ICON_WORDPRESS("icon-wordpress", "\uEab4"),
    /**
     * Joomla icon
     */
    ICON_JOOMLA("icon-joomla", "\uEab5"),
    /**
     * Ello icon
     */
    ICON_ELLO("icon-ello", "\uEab6"),
    /**
     * Blogger icon
     */
    ICON_BLOGGER("icon-blogger", "\uEab7"),
    /**
     * Blogger icon
     */
    ICON_BLOGGER2("icon-blogger2", "\uEab8"),
    /**
     * Tumblr icon
     */
    ICON_TUMBLR("icon-tumblr", "\uEab9"),
    /**
     * Tumblr icon
     */
    ICON_TUMBLR2("icon-tumblr2", "\uEaba"),
    /**
     * Yahoo icon
     */
    ICON_YAHOO("icon-yahoo", "\uEabb"),
    /**
     * Yahoo icon
     */
    ICON_YAHOO2("icon-yahoo2", "\uEabc"),
    /**
     * Tux icon
     */
    ICON_TUX("icon-tux", "\uEabd"),
    /**
     * Apple Inc. icon
     */
    ICON_APPLEINC("icon-appleinc", "\uEabe"),
    /**
     * Finder icon
     */
    ICON_FINDER("icon-finder", "\uEabf"),
    /**
     * Android icon
     */
    ICON_ANDROID("icon-android", "\uEac0"),
    /**
     * Windows icon
     */
    ICON_WINDOWS("icon-windows", "\uEac1"),
    /**
     * Windows8 icon
     */
    ICON_WINDOWS8("icon-windows8", "\uEac2"),
    /**
     * Soundcloud icon
     */
    ICON_SOUNDCLOUD("icon-soundcloud", "\uEac3"),
    /**
     * Soundcloud icon
     */
    ICON_SOUNDCLOUD2("icon-soundcloud2", "\uEac4"),
    /**
     * Skype icon
     */
    ICON_SKYPE("icon-skype", "\uEac5"),
    /**
     * Reddit icon
     */
    ICON_REDDIT("icon-reddit", "\uEac6"),
    /**
     * Hackernews icon
     */
    ICON_HACKERNEWS("icon-hackernews", "\uEac7"),
    /**
     * Wikipedia icon
     */
    ICON_WIKIPEDIA("icon-wikipedia", "\uEac8"),
    /**
     * Linked in icon
     */
    ICON_LINKEDIN("icon-linkedin", "\uEac9"),
    /**
     * LinkedIn icon
     */
    ICON_LINKEDIN2("icon-linkedin2", "\uEaca"),
    /**
     * Last FM icon
     */
    ICON_LASTFM("icon-lastfm", "\uEacb"),
    /**
     * Last FM icon
     */
    ICON_LASTFM2("icon-lastfm2", "\uEacc"),
    /**
     * Delicious icon
     */
    ICON_DELICIOUS("icon-delicious", "\uEacd"),
    /**
     * Stumbleupon icon
     */
    ICON_STUMBLEUPON("icon-stumbleupon", "\uEace"),
    /**
     * Stumbleupon icon
     */
    ICON_STUMBLEUPON2("icon-stumbleupon2", "\uEacf"),
    /**
     * Stackoverflow icon
     */
    ICON_STACKOVERFLOW("icon-stackoverflow", "\uEad0"),
    /**
     * Pinterest icon
     */
    ICON_PINTEREST("icon-pinterest", "\uEad1"),
    /**
     * Pinterest icon
     */
    ICON_PINTEREST2("icon-pinterest2", "\uEad2"),
    /**
     * Xing icon
     */
    ICON_XING("icon-xing", "\uEad3"),
    /**
     * Xing icon
     */
    ICON_XING2("icon-xing2", "\uEad4"),
    /**
     * Flattr icon
     */
    ICON_FLATTR("icon-flattr", "\uEad5"),
    /**
     * Foursquare icon
     */
    ICON_FOURSQUARE("icon-foursquare", "\uEad6"),
    /**
     * Yelp icon
     */
    ICON_YELP("icon-yelp", "\uEad7"),
    /**
     * Paypal icon
     */
    ICON_PAYPAL("icon-paypal", "\uEad8"),
    /**
     * Chrome icon
     */
    ICON_CHROME("icon-chrome", "\uEad9"),
    /**
     * Firefox icon
     */
    ICON_FIREFOX("icon-firefox", "\uEada"),
    /**
     * IE Icon
     */
    ICON_IE("icon-IE", "\uEadb"),
    /**
     * Edge icon
     */
    ICON_EDGE("icon-edge", "\uEadc"),
    /**
     * Safari icon
     */
    ICON_SAFARI("icon-safari", "\uEadd"),
    /**
     * Opera icon
     */
    ICON_OPERA("icon-opera", "\uEade"),
    /**
     * PDF Icon
     */
    ICON_FILE_PDF("icon-file-pdf", "\uEadf"),
    /**
     * Open Office icon
     */
    ICON_FILE_OPENOFFICE("icon-file-openoffice", "\uEae0"),
    /**
     * Word icon
     */
    ICON_FILE_WORD("icon-file-word", "\uEae1"),
    /**
     * Excel icon
     */
    ICON_FILE_EXCEL("icon-file-excel", "\uEae2"),
    /**
     * Libre Office icon
     */
    ICON_LIBREOFFICE("icon-libreoffice", "\uEae3"),
    /**
     * Five icon
     */
    ICON_HTML_FIVE("icon-html-five", "\uEae4"),
    /**
     * Five icon
     */
    ICON_HTML_FIVE2("icon-html-five2", "\uEae5"),
    /**
     * CSS icon
     */
    ICON_CSS3("icon-css3", "\uEae6"),
    /**
     * Git icon
     */
    ICON_GIT("icon-git", "\uEae7"),
    /**
     * Codepen icon
     */
    ICON_CODEPEN("icon-codepen", "\uEae8"),
    /**
     * SVG icon
     */
    ICON_SVG("icon-svg", "\uEae9"),
    /**
     * IcoMoon Icon
     */
    ICON_ICOMOON("icon-IcoMoon", "\uEaea");

    private final String unicode;
    private final String glyphName;

    IcoMoonFontEnum(String glyphName, String unicode) {
        this.unicode = unicode;
        this.glyphName = glyphName;
    }

    /**
     * Get the unicode value
     *
     * @return the unicode value
     */
    public String getUnicode() {
        return unicode;
    }

    /**
     * Get the name of the glyph
     *
     * @return the glyph name
     */
    public String getGlyphName() {
        return glyphName;
    }
}