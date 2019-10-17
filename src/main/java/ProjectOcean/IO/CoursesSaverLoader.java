package ProjectOcean.IO;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.CourseFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoursesSaverLoader implements ICourseSaveLoader{

    private static String fileName = "courses.json";
    private static JSONParser parser = new JSONParser();

    public CoursesSaverLoader() {
    }

    /**
     * Loads a Map from "courses.json" in user hom dir if it cant find the file it creates a new empty one
     *
     * @return returns a <code>Map<UUID, Course></code>
     */
    @Override
    public List<Course> loadCoursesFile() throws CoursesNotFoundException{
        try {
            return readFromFile();
        } catch (ParseException e) {
            throw new CoursesNotFoundException();
        } catch (IOException e) {
            throw new CoursesNotFoundException();
        }
    }

    /**
     * Creates a file filled with all default courses
     */
    @Override
    public void createCoursesFile() {
        new File(getHomeDirPath() + fileName);
        savePreMadeCourses();
    }

    /**
     * The method tha reads the file and creates the Map
     *
     * @return returns a <code>Map<UUID, Course></code>
     * @throws IOException
     * @throws ParseException
     */
    private static List<Course> readFromFile() throws IOException, ParseException {

        //creates a file with the path to the courses.json
        File file = new File(getHomeDirPath(), getFileName());

        //Map to return when method is done
        List<Course> courses = new ArrayList<>();

        //Creates a filereader which reads the courses.json and creates it as a jsonArray
        FileReader fileReader = new FileReader(file);
        Object parsed = parser.parse(fileReader);
        JSONArray studyPlans = (JSONArray) parsed;

        //loops through all "courses"
        for (Object object : studyPlans) {

            Course course = createCourseFronJSONObject(object);
            courses.add(course);

        }

        return courses;
    }

    private static Course createCourseFronJSONObject(Object object){
        //casts the "course" to a jsonObject to be able to access the info
        JSONObject jsonObject = (JSONObject) object;

        //loops through all required courses inorder to add them to the course in the constructor
        JSONArray jArr = (JSONArray) jsonObject.get("requiredCourses");
        List<String> requiredCourses = new ArrayList<>();
        for (Object obj : jArr) {
            requiredCourses.add((String) obj);
        }

        Course course = new Course(
                (String) jsonObject.get("courseCode"),
                (String) jsonObject.get("courseName"),
                (String) jsonObject.get("studyPoints"),
                (String) jsonObject.get("studyPeriod"),
                (String) jsonObject.get("examiner"),
                (String) jsonObject.get("examinationMeans"),
                (String) jsonObject.get("language"),
                requiredCourses,
                (String) jsonObject.get("coursePMLink"),
                (String) jsonObject.get("courseDescription")
        );
        return course;
    }

    /**
     *
     * @return returns the users home directory
     */
    static String getHomeDirPath() {
        return System.getProperty("user.home") + File.separatorChar + ".CoursePlanningSystem";
    }

    /**
     *
     * @return returns the filename which holds courses
     */
    static String getFileName() {
        return fileName;
    }

    /**
     * Saves a list of courses to a "courses.json" file in the user home dir.
     */
    static void savePreMadeCourses() {
        //creates the "main" array which contains all courses
        JSONArray jsonCourses = new JSONArray();

        List<Course> courses = generatePreDefinedCourses();

        for (Course course: courses) {

            //creates a json object which represents a course
            JSONObject jsonCourse = new JSONObject();

            jsonCourse.put("courseCode", course.getCourseCode());
            jsonCourse.put("courseName", course.getCourseName());
            jsonCourse.put("studyPoints", course.getStudyPoints());
            jsonCourse.put("studyPeriod", course.getStudyPeriod());
            jsonCourse.put("examiner", course.getExaminer());
            jsonCourse.put("examinationMeans", course.getExaminationMeans());
            jsonCourse.put("language", course.getLanguage());
            JSONArray requiredCourses = new JSONArray();
            for (String s : course.getRequiredCourses()) {
                requiredCourses.add(s);
            }
            jsonCourse.put("requiredCourses", requiredCourses);
            jsonCourse.put("coursePMLink", course.getCoursePMLink());
            jsonCourse.put("courseDescription", course.getCourseDescription());

            jsonCourses.add(jsonCourse);

        }

        writeToFile(jsonCourses);

    }

    /**
     * Creates courses a predefined list of courses
     * @return returns a list of courses
     */
    public static List<Course> generatePreDefinedCourses(){
        List<Course> courses = new ArrayList<>();

        courses.add(createCourse("EDA433","Grundläggande datorteknik", "7.5", "1", "Rolf Snedsböl", "Tenta + Laborationer", "Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=27769&parsergrp=2", "Syfte:\n" +
                "Kursen ska ge förståelse av datorns uppbyggnad och funktionssätt och därigenom en mycket god teoretisk och praktisk grund för fortsatta studier i såväl datortekniska som programmeringstekniska kurser.\n" +
                "\n" +
                "Innehåll:\n" +
                "* Digitalteknikens grundläggande element och begrepp och olika talsystem.\n" +
                "* Boolesk algebras användning för konstruktion av kombinatoriska nät och synkrona sekvensnät.\n" +
                "* Datorns digitala byggblock (ALU, dataväg, styrenhet, minne, in- och ut- enheter).\n" +
                "* Den traditionsenliga processorns uppbyggnad (dataväg och styrenhet med instruktionsuppsättning) som en synkront arbetande digitalmaskin.\n" +
                "* Kodning i maskinspråk och assemblerspråk."));

        courses.add(createCourse("TDA548","Grundläggande programvaruutveckling", "7.5", "1", "Joachim von Hacht", "Tenta + Laborationer", "Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=28460&parsergrp=2", "Syfte:\n" +
                "Den här kursen är en introduktion till imperativ programmering och programvaruutveckling. Kursen syftar till att ge grundläggande färdigheter i programmeringsmässig problemlösning med imperativa metoder, och ge en första inblick i programmerandet som hantverk.\n" +
                "\n" +
                "Innehåll:\n" +
                "I kursen används programspråket Java. Följande moment behandlas:\n" +
                "* variabler, uttryck och satser, prioritet, metoder, parameteröverföring, rekursion\n" +
                "* fält och inläsning från en användare\n" +
                "* enkla typer och referenstyper, typomvandling\n" +
                "* grundläggande objektorienterade begrepp såsom klasser, objekt, metoder och instansvariabler\n" +
                "* olika tester på likhet, kanonisk form för klasser\n" +
                "* grundläggande arv och gränssnitt (interfaces)\n" +
                "* standardklasser för grafiska användargränssnitt\n" +
                "* händelser och lyssnare\n" +
                "* felhantering med hjälp av undantag\n" +
                "* sökning på Internet efter dokumentation för standardklasser\n" +
                "* några av klasserna i Javas API"));

        courses.add(createCourse("TDA552","Objektorienterad programmering och design", "7.5", "2", "Alex Gerdes", "Munta + Inlämningsuppgift", "Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=27211&parsergrp=2", "Syfte:\n" +
                "Programvaruutveckling är centralt för en civilingenjör inom informationsteknik. Kursen presenterar det objektorienterade programmeringsparadigmet och lägger stor vikt vid design av objektorienterade program.\n" +
                "\n" +
                "Innehåll:\n" +
                "Kursen presenterar det objektorienterade programmeringsparadigmet och lägger stor vikt vid programkonstruktion och design.\n" +
                "\n" +
                "Begreppsapparat och teknik utökas och fördjupas: metoder, objekt, abstrakta- och anonyma klasser, initiering, polymorfism, överlagring och överskuggning,implementations- och gränssnittsarv, användning generiska typer, konstruktion av enkla generiska klasser, felhantering, immutabilitet och defensive copying, och introduktion till trådar och trådsäkerhet m.m.\n" +
                "\n"));


        courses.add(createCourse("TMV200","Diskret matematik", "7.5", "2", "Mårten Wadenbäck", "Tenta", "Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=28303&parsergrp=2", "Syfte:\n" +
                "Kursen ger grundläggande kunskaper om diskreta matematiska strukturer som behövs för högskolestudier, främst sådana som har anknytning till datorer och programering.\n" +
                "\n" +
                "Innehåll:\n" +
                "Kursen är uppdelad i tre teman. Inom varje tema studeras relevanta matematiska begrepp. Vissa kursmoment, såsom bevisföring, återkommer i fler teman. Kursens teman är:\n" +
                "* Logik, relationer och funktioner, och bevis\n" +
                "* Heltalsaritmetik och RSA-algoritmen\n" +
                "* Kombinatorik och grafer\n" +
                "Vissa grundläggande begrepp såsom mängder och funktioner introduceras i den introduktionskurs som föregår denna kurs, men de fördjupas och spelar en roll även i denna kurs."));

        courses.add(createCourse("DAT017","Maskinorienterad programmering", "7.5", "3", "Roger Johansson", "Tenta + Laborationer", "Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=28459&parsergrp=2", "Syfte:\n" +
                "Kursens syfte är att vara en introduktion till konstruktion av små inbyggda system och att ge en förståelse för hur imperativa styrstrukturer översätts till assembler samt för de svårigheter som uppstår vid programmering av händelsestyrda system med flera indatakällor.\n" +
                "\n" +
                "Innehåll:\n" +
                "Schemalagd undervisning bes"));

        courses.add(createCourse("LSP310","Kommunikation och ingenjörskompetens", "7.5", "3", "Fia Christina Börjeson", "Inlämning + Muntlig presentation", "Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=28459&parsergrp=2", "Syfte:\n" +
                "Under första året syftar kursen Kommunikation och ingenjörskompetens till att tydliggöra rollen som IT-ingenjör, vad som krävs av en ingenjör och skapa motivation för fortsatta studier.\n" +
                "Vidare syftar kursen till att fördjupa studenternas kommunikativa kompetenser och träna skrivande och muntlig framställning. Kursen avser att skapa språklig medvetenhet och arbeta med varseblivning om vilka faktorer som styr och utgör effektiv kommunikation.\n" +
                "\n" +
                "Innehåll:\n" +
                "Kommunikationskursen omfattar både muntlig och skriftlig framställning. De moment som rör skrivande och skrivprocessen är t.ex. textmedvetenhet och färdighet i textproduktion. Vidare finns inslag av textanalys, retorik, källkritik, textlayout. Viss betoning ligger på rapportskrivning och innefattar genomgång och diskussion av skrivregler med avseende på den tekniska rapporten och vetenskaplig text.\n" +
                "\n" +
                "Genomgående innehåller kursen diskussioner kring och metodik för textförbättring, anpassning, av information till olika målgrupper och kommunikationssituationer och från text till tal. Stil- och språkriktighetsfrågor i skrift såväl som tal diskuteras genomgående i syfte att ge en allmän orientering i det samtida språket.\n" +
                "\n" +
                "Utöver de större skrivuppgifterna som ligger till grund för examinationen genomförs under kursens gång också en hel del skrivövningar, både i klassrummet och mellan lektionerna i form av inlämningsuppgifter.\n" +
                "\n" +
                "Inom ramen för Ingenjörskompetensdelen bjuds personer från näringsliv och högskola in för att få en översiktlig bild av yrkesroller och kompetenser kopplade till Informationsteknik. Föreläsningarna har olika tema som förutom olika yrkesroller kan handla om entreprenörskap, konsultrollen, hållbar utveckling osv. Genom reflektion kopplar studenten det som sagts till sig själv och sin kommande roll som ingenjör.\n" +
                "Inom ramen för gruppdynamik betonas innehåll/process, värderingar, gruppnormer, gruppers utvecklingsprocess, målfokusering, grupproller, feedback, personliga styrkor."));

        courses.add(createCourse("TMV206","Linjär Algebra", "7.5", "3", "Lukás Malý", "Tenta + Laborationer", "Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=27506&parsergrp=2", "Syfte:\n" +
                "Linjär algebra är ett matematiskt verktyg som används inom alla vetenskaper som använder matematik och är därför ett oundgängligt redskap för i stort sett alla civilingenjörer. Detta gäller inte minst för ingenjörer inom datavetenskap som har massor av tillämpningar av linjär algebra. Det slutgiltliga syftet är därför att du som ingenjör skall vara redo att betrakta nya problem utifrån dina kunskaper i linjär algebra och kunna angripa problemen med dessa nya verktyg.\n" +
                "\n" +
                "Innehåll:\n" +
                "Geometriska vektorer:\n" +
                "Skalärprodukt, vektorprodukt, linjärkombinationer, ortogonal projektion, koordinatsystem, linjer och plan.\n" +
                "Matrisalgebra:\n" +
                "Addition, multiplikation, transponat, identitet och invers.\n" +
                "Linjära avbildningar:\n" +
                "Matrisrepresentation, geometriska avbildningar, sammansättning och invers.\n" +
                "Vektorer i godtycklig dimension:\n" +
                "Generalisering av de geometriska begreppen till godtycklig dimension.\n" +
                "Linjära ekvationssystem:\n" +
                "Matrisform, lösningsmängd, Gausselimination och minstakvadratlösning.\n" +
                "Determinant:\n" +
                "Definition, beräkning och geometrisk tolkning.\n" +
                "Baser och linjärt oberoende:\n" +
                "Byta bas i koordinatsystem och för linjär avbildning, ON-bas och ON-matris.\n" +
                "Egenvärden och egenvektorer:\n" +
                "Karakteristisk ekvation, spektralsatser, diagonalisering och potensmetoden.\n" +
                "Grafer och grannmatriser:\n" +
                "Grafbegrepp, övergångsmatris, slumpvandring, stationär fördelning och Markovkedja.\n"));


        courses.add(createCourse("DAT216","Design och konstruktion av grafiska gränssnitt", "7.5", "4", "Olof Torgersson", "Tenta + Projektrapport", "Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=27928&parsergrp=2", "Syfte:\n" +
                "I princip alla datorprogram som ska användas av människor använder sig idag av grafiska gränssnitt. Att utveckla dessa innebär inte bara en förståelse av hur dessa kan implementeras utan även att man har en förståelse för de som ska använda programmen. Kursen ger praktisk erfarenhet i dessa två aspekter genom ett grupprojekt som ger en fördjupning av delmomentet kring grafiska komponenter från kursen Objektorienterad programvaruutveckling. Projektets mål är att utveckla en applikation för en specifik användargrupp och att genom att låta dessa testa programmet iterativt förbättra det.\n" +
                "\n" +
                "Innehåll:\n" +
                "Kursen innehåller en genomgång av standardklasserna i ett välutvecklat\n" +
                "grafiskt bibliotek, en översikt över vilka riktlinjer som krävs för att\n" +
                "skapa lättanvända gränssnitt samt metoder för att iterativt utveckla och\n" +
                "förbättra ett gränssnitt."));


        courses.add(createCourse("MVE045","Matematisk Analys", "7.5", "1", "Zoran Konkoli", "Tenta", "Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=27512&parsergrp=2", "Syfte:\n" +
                "Kursens syfte är att, tillsammans med övriga matematikkurser, ge en matematisk allmänbildning användbar i fortsatta studier och yrkesverksamhet. Kursen skall ge kunskaper i envariabelanalys nödvändiga för övriga kurser inom IT-programmet.\n" +
                "\n" +
                "Innehåll\n" +
                "Grundläggande analys i en variabel: elementära funktioner, gränsvärdesbegeppet, kontinuitet och deriverbarhet för reella funktioner, medelvärdessatsen, Riemannintegralen, primitiva funktioner och kopplingen till integraler, tillämpningar av intregralberäkningar på volymer av kroppar och längden av kurvor, enklare differentialekvationer, Taylorutvecklingar och approximationer av funktioner, komplexa tal"));


        courses.add(createCourse("TDA416","Datastrukturer och algoritmer", "7.5","2","Erland Holmström", "Tenta + Laborationer","Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=27945&parsergrp=2", "Syfte:\n" +
                "Kursen skall ge goda kunskaper om vanligt förekommande abstrakta datatyper, datastrukturer och algoritmer, samt hur dessa används.\n" +
                "\n" +
                "Innehåll:\n" +
                "Abstrakta datatyper. Enkel komplexitetsanalys av imperativ kod. Vanliga datastrukturer som fält, listor, träd och hashtabeller samt hur dessa kan användas för att implementera abstrakta datatyper som köer, prioritetsköer, lexika och grafer. Standardalgoritmer på dessa datastrukturer och deras resurskrav. Iteratorer. Metoder för sortering. Standardbibliotek för datastrukturer och algoritmer. Något om vanliga tekniker för algoritmdesign."));

        courses.add(createCourse("DAT256","Software engineering project", "7.5","4","Jan-Philipp Steghöfer", "Rapport","Engelska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=28608&parsergrp=2", "Syfte:\n" +
                "Kursen syftar till att ge studenterna grundläggande kunskap och en första erfarenhet av mjukvaruutveckling genom praktiskt projektarbete.\n" +
                "\n" +
                "Innehåll:\n" +
                "Kursen ger en praktisk introduktion till mjukvaruutveckling. Studenterna kommer att arbeta med ett öppet problem som definieras av en eller flera intressenter utanför studenternas arbetslag. Studenter får alltså inte själva bestämma projektets inriktning. För att hantera den utmaningen lär sig studenterna:\n" +
                "\n" +
                "* en mjukvaruutvecklingsprocess för att strukturera arbete\n" +
                "* specificering och utvärdering av krav och samarbete med intressenter för att det som levereras ska anses värdefullt\n" +
                "* nya teknologier och verktyg och lämpliga sätt att använda de på för att realisera sitt värdeerbjudande genom att utforma egna lärandestrategier\n" +
                "* organisera sig själva i lag för att nå gemensamma mål med begränsade resurser\n" +
                "* reflektera över sitt egna arbete och lärande för att möjliggöra kontinuerlig förbättring av sitt egna arbetssätt"));

        courses.add(createCourse("MVE051","Matematisk statistik och diskret matematik", "7.5","4","Nancy Abdallah", "Tenta + Inlämningsuppgifter","Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=28161&parsergrp=2", "Syfte:\n" +
                "Kursen avser att ge\n" +
                "- Förståelse av grundläggande sannolikhetslära, statistik och kombinatorik som är viktig för tekniska studier i allmänhet och studier i informationsteknologi i synnerhet.\n" +
                "- Färdighet i att förstå och använda det matematiska språket.\n" +
                "- Förmåga att kommunicera matematik.\n" +
                "\n" +
                "Innehåll\n" +
                "Kursen består av olika teman. Inom varje tema studeras relevanta matematiska begrepp. Samma kursmoment återkommer, i fördjupad framställning, i flera teman.\n" +
                "De moment som ingår i kursen är:\n" +
                "- Sannolikhetslära och Markovkedjor -- stokastiska variabler, väntevärde, varians, korrelation, betingade sannolikheter, stora talens lag, centrala gränsvärdessatsen.\n" +
                "- Statistik -- skattningar, konfidensintervall, test.\n" +
                "- Kombinatorik -- kombinationer, permutationer, genererande funktioner.\n" +
                "\n" +
                "I sannolikhetsläran läggs tonvikten på diskreta modeller."));

        courses.add(createCourse("ITS024","Teknik för ett hållbart globalt samhälle", "7.5","1","Catharina Landström", "Tenta + Inlämningsuppgifter","Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=28433&parsergrp=2", "Syfte:\n" +
                "Kursen syftar till att både introducera grundläggande kunskaper inom området hållbar utveckling samt metoder och verktyg för uthålligare IT-utveckling och IT-användning.\n" +
                "\n" +
                "Kursen syftar till att ge en förståelse för de komplexa sammanhang som påverkar hållbarhetsanpassning av produkter och tjänster sett ur ett livscykelperspektiv samt att erbjuda möjligheter att reflektera kring IT-ingenjörens möjligheter att på olika nivåer och områden medverka till ett hållbart samhälle.\n" +
                "\n" +
                "En viktig utgångspunkt är att kursens olika moment skall ge kunskaper och möjligheter att träna på att bryta ner komplexa hållbarhetsfrågeställningar till hanterbara delproblem som ligger inom IT-ingenjörens påverkansområden.\n" +
                "\n" +
                "Innehåll:\n" +
                "- Fördjupning i hållbar utveckling\n" +
                "- IT-ingenjörens roll i ett hållbart samhälle\n" +
                "- Att formulera utvecklingsprojekt utifrån hållbarhetsprinciper\n" +
                "- Historiska perspektiv på IT och hållbar utveckling\n" +
                "- Att genomföra en nulägesanalys\n" +
                "- Konkreta exempel på pågående IT- och hållbarhetsprojekt\n" +
                "- Livscykelanalyser\n" +
                "- Politiska styrmedel\n" +
                "- Ekonomisk tillväxt, frikoppling och rekyleffekt\n" +
                "- Metoder för urval och genomförande av IT- och hållbarhetsprojekt"));

        courses.add(createCourse("TDA593","Modelldriven mjukvaruutveckling", "7.5", "2", "Patrizio Pelliccione", "Projeckt","Engelska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=28506&parsergrp=2", "Syfte:\n" +
                "Model-driven system development (MDSD) has become a popular way of building software systems. The promise of MDSD is to improve the quality of the developed systems, including extensibility, reusability, and maintainability. The purpose of this course is to show how models can be profitably used during the development of software systems.\n" +
                "\n" +
                "Innehåll:\n" +
                "In this course we will critically analyse the use of models during system development processes. We will introduce different types of models and we will discuss on the benefits and limitations of using them in practical environments. We will discuss on how to identify the right abstraction level, according to the purpose of the models and to the intended consumers.\n" +
                "\n" +
                "We will introduce both static and behavioural models, we will introduce executable models, and we will explain how to generate code from models."));

        courses.add(createCourse("TDA518","Kommunikation engelska och ingenjörskompetens", "7.5", "4", "Raffaella Negretti", "Inlämningsupgift ","Svenska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/Sidor/SokProgramutbudet.aspx?course_id=27880&parsergrp=2", "Syfte:\n" +
                "Ingenjörskompetensmomentet av kursen syftar till att göra teknologen medveten om de kompetenser som behövs för framtida studier och yrkesliv samt öka förmågan att reflektera över hur hon/han, med sina individuella förutsättningar, på ett systematiskt sätt kan bygga upp samt dokumentera sin utveckling.\n" +
                "\n" +
                "Kursens kommunikationskompetensdel sker på engelska, med syfte att öka elevernas förståelse av olika genrer av kommunikation i sin disciplin, som akademisk och yrkesmässig, och deras förmåga att tillämpa denna kunskap för att kommunicera på ett effektivt sätt på engelska både i tal och skrift, för att förbereda dem inför framtida studier och yrkesliv.\n" +
                "\n" +
                "Innehåll:\n" +
                "Kursdelen ingenjörskompetens börjar i lp 3 och innehåller flera olika delar; karriärplanering i grupp, seminarier/workshops och ett studiebesök. Karriärplanering i grupp syftar till att reflektera över den egna rollen. Övningar kring individens starka sidor, intressen, värderingar, färdigheter och viktiga erfarenheter görs både individuellt och i grupp. Möten med arbetslivet sker i olika former som seminarier/workshops och ett studiebesök i grupp hos en IT-ingenjör i industrin. Studiebesöket följs av rapportskrivning och en powerpoint-presentation. Sista momentet är en individuell självreflektion där den egna karriärplaneringen ska kopplas ihop med studiebesöket.\n" +
                "\n" +
                "Den engelska delen av kursen syftar till att utveckla en mängd kritiskt tänkande som är nödvändiga för effektiv kommunikation i professionella och akademiska sammanhang. Även om fokus är på engelska, är målet att utveckla läsning, skrivning och kommunikativa strategier som kan överföras till andra sammanhang och språk. Av denna anledning omfattar kursen aktiviteter som läsning och analys av ämnes relevanta vetenskapliga texter (till exempel konferensbidrag), översikt och praktik av effektiva retoriska strukturer i skrift, peer-review och reflektion för planering och självbedövning, och individuell engelsk grammatik praktiken. Kursen kräver av studenterna att tillämpa dessa kunskaper i skrift och i tal, genom att producera en text som kritiskt granskar kunskaper inom en disciplin-relevant område och argumenterar för en synpunkt, och genom att presentera detta arbete och ge feedback till andra."));

        courses.add(createCourse("DAT321","Datavetenskap", "7.5", "4", "Anders Bölinge", "Tenta","Svenska", new ArrayList<>(), "www.chalmers.se", "Lorem ipsum"));
        courses.add(createCourse("DAT321","Datavetenskap", "7.5", "4", "Anders Bölinge", "Tenta","Svenska", new ArrayList<>(), "www.chalmers.se", "Lorem ipsum"));
        courses.add(createCourse("BAT123","Beroendespecifika paradigmer", "7.5","3","Anders Bölinge", "Tenta + Laborationer","Svenska", new ArrayList<>(), "www.chalmers.se", "Lorem ipsum"));
        courses.add(createCourse("CAT123","Complex system", "7.5", "2", "Anders Fölinge", "Tenta","Engelska", new ArrayList<>(), "www.chalmers.se", "Lorem ipsum"));
        courses.add(createCourse("FAT321","Fysik för ingenjörer", "7.5", "1", "Anders Brölinge", "Tenta","Svenska", new ArrayList<>(), "www.chalmers.se", "Lorem ipsum"));

        return courses;
    }

    private static Course createCourse(String courseCode, String courseName, String studyPoints, String studyPeriod, String examiner, String examinationMeans, String language, List<String> requiredCourses, String coursePMLink, String courseDescription){
        return new Course(
                courseCode,
                courseName,
                studyPoints,
                studyPeriod,
                examiner,
                examinationMeans,
                language,
                requiredCourses,
                coursePMLink,
                courseDescription
        );
    }

    /**
     * The actual method that creates the file and puts a json array in it
     *
     * @param jsonArray is the array being saved
     */
    private static void writeToFile(JSONArray jsonArray) {
        try (FileWriter file = new FileWriter(new File(getHomeDirPath(), fileName))) {

            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
