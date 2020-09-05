- Format by Valoeghese
- Readme written by Halotroop2288, based off of Valoeghese's description in Discord PMs

# Segregated Ordinal Data v2

Basically, SOD splits binary data into named sections.

Data is stored in each section by index, rather than by name.

There are no sub-sections, but a similar effect can be achieved by creating a section which is a list of section names.

 - SOD (.sod) is not by default compressed, however there is an “official” compressed version: G-zipped SOD (.gsod)
 - Each file starts with the long `0xA77D1E` as the “magic” number to indicate to the parser that it is SOD.

After which, data is stored in this format:
```
- Type (1 byte)
- Data (depends on the type)
```

The first data entry must be one of the various section types, since **all data must exist in a section**.

There are normal data sections and array data sections,
array sections exist for compactness, of course.

Normal data sections have the ID `0`.

The data it stores is the name of the section

*Until another section is declared,* each proceeding data is part of that section.

All strings are **UTF-8**-formatted.

The types of data that exist in a section are:
 - byte (ID `1`)
 - short (ID `2`)
 - int (ID `3`)
 - long (ID `4`)
 - float (ID `5`)
 - double (ID `6`)
 - string (ID `7`)

Each array section has this format:
```
- Byte ID
- UTF-8 name
- Array length (an integer)
- Array data (in the amount of the length supplied above)
```

The array data section IDs are:
 - byte array (ID `8`)
 - short array (ID `9`)
 - int array (ID `10`)
 - long array (ID `11`)
 - float array (ID `12`)
 - double array (ID `13`)
 - string array (ID `14`)

Booleans are stored as a `1` (`true`) or a `0` (`false`).

Enums can be stored as an int (by ordinal).

For more information, see the [SOD Java Parser](https://github.com/valoeghese/SOD-Java/blob/2.0/src/tk/valoeghese/sod/Parser.java)
