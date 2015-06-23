Hacker's Keyboard Modded
======
**HK Modded** is a [Hacker's Keyboard](https://code.google.com/p/hackerskeyboard/) clone. When Tasker integration is enabled, HK Modded receives keyboard commands from specific Tasker plugins and executes them.

#### Files added to the original code and their usage
* MockCallback.java - for key event testing
* PrefDatabase.java - a database that keeps track of Tasker integration preference changes
* PrefDbHelper.java - controls the interaction between the contact provider and the preference database
* PrefProvider.java - a content provider used by Tasker plugins to determine is Tasker integration option is enabled or not
* TaskerNotificationReceiver.java - a broadcast receiver that listens for keyboard commands comming from Tasker plugins 

#### Edited files and the reason why they were modified
* LatinIME.java - edited for broadcast receiver registration/unregistration
* strings.xml - added Tasker-related labels used in HK Modded settings
* prefs.xml - added Tasker integration preference 
* AndroidManifest.xml - registered content provider

## Hacker's Keyboard User's Guide
* see [USER'S GUIDE](https://code.google.com/p/hackerskeyboard/wiki/UsersGuide) from the original repo

## Permissions
* Read user defined dictionary [READ_USER_DICTIONARY] - Allows an application to read any words, names and phrases that the user may have stored in the user dictionary.

* EXTRA/CUSTOM
Control vibrator [VIBRATE] - Allows the application to control the vibrator.

* EXTRA/CUSTOM
rite to user defined dictionary [WRITE_USER_DICTIONARY] - Allows an application to write new words into the user dictionary.

## How to build from source
* see [INSTRUCTIONS](https://code.google.com/p/hackerskeyboard/wiki/BuildingFromSource) from the original repo

## Version 
* Version 1.37 (same with the current version of HK found in [Google PlayStore](https://play.google.com/store/apps/details?id=org.pocketworkstation.pckeyboard))

## Downloads
* see [RELEASES](https://github.com/annelagang/HK-Modded/tags) for the apks and source code

License
-------

    Copyright (c) Klaus Weidner <https://code.google.com/p/hackerskeyboard/>
    Copyright (c) 2015 Anne Lagang <https://github.com/annelagang/HK-Modded>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## Contact
#### Hacker's Keyboard (Klaus Weidner)
* Homepage: https://code.google.com/p/hackerskeyboard/
* e-mail: pocketworkstation@gmail.com
* Forum: https://groups.google.com/forum/#!forum/hackerskeyboard

#### Hacker's Keyboard Modded (Anne Lagang)
* Homepage: https://github.com/annelagang/HK-Modded
* e-mail: anne.lagang@gmail.com
