# Table of contents

1. [Clone the repository](#cloneTheRepository)
    1. [Tools](#tools)
        1. [For Windows](#forWindows)
        2. [For MacOS](#forMacOS)
    2. [Git Configuration](#gitConfiguration)
    3. [By HTTPS](#byHTTPS)
    4. [By SSH](#bySSH)
        1. [Generate SSH for Windows](#generateSSHKeyForWindows)
        2. [Generate SSH for MacOS](#generateSSHKeyForMacOS)

-----------------------


<a name="cloneTheRepository"></a>
# Clone the repository

<a name="tools"></a>
## Tools

<a name="forWindows"></a>
### For Windows
Download the `Git CMD` tool in order to clone this repository from terminal

- [Download Git CMD](https://git-scm.com/downloads)

<a name="forMacOS"></a>
### For MacOS
A very good alternative to the default Mac's terminal is `iTerm`, also install brew for manage the terminal's packages.

- [Download iTerm](https://www.iterm2.com/downloads.html)
- [Download Brew](https://brew.sh/)


<a name="gitConfiguration"></a>
## Git Configuration
Apply the following command if you have not configured `Git` in your machine:

```bash
git config --global user.name "<YOUR NAME>"
git config --global user.email "<YOUR AZTRAZENECA EMAIL>"
```

To make sure that the above commands were applied correctly type the following command in terminal:

```bash
git config --list | grep user
```

### Removing an entry global configuration
If you made a mistake writing any `git global variables` type the following command:

```bash
git config --global --unset <variable>
```

> Where `<variable>` could be either "**user.name**" or "**user.email**"

<a name="byHTTPS"></a>
## By HTTPS
Cloning the repository by `HTTPS` protocol mean every time you need to upload a change or clone 
this repository it will ask you for your credentials

Type the following command in the terminal:

```bash
git clone https://bitbucket.astrazeneca.net/scm/dev-templates/selenium-ui-tests-framework-template.git
```

:eyes: The credentials are your `PRID` (without astrazeneca domain) and your current password

> Note: If you typed wrong credentials on Windows you can change it from [Credential Manager](https://support.microsoft.com/en-us/help/4026814/windows-accessing-credential-manager) 

<a name="bySSH"></a>
## By SSH
Cloning the repository by `SSH` protocol mean every time you need to upload a change or clone
this repository it will not ask you for credentials.
In order to clone by `SSH` first you need to add your personal `SSH Key` to bitbucket in the following link:

- [Bitbucket SSH Key](https://bitbucket.astrazeneca.net/plugins/servlet/ssh/account/keys/add)

Once the previous step is done, type the following command in terminal:

```bash
git clone ssh://git@bitbucket.astrazeneca.net:7999/dev-templates/selenium-ui-tests-framework-template.git
```

<a name="generateSSHKeyForWindows"></a>
### Generate SSH for Windows
Follow this :point_right: [guide](https://docs.joyent.com/public-cloud/getting-started/ssh-keys/generating-an-ssh-key-manually/manually-generating-your-ssh-key-in-windows#git-bash)
in order to generate an SSH key for Windows.

<a name="generateSSHKeyForMacOS"></a>
### Generate SSH for MacOS
Follow this :point_right: [guide](https://secure.vexxhost.com/billing/index.php/knowledgebase/171/How-can-I-generate-SSH-keys-on-Mac-OS-X.html)
in order to generate an SSH key for MacOS.