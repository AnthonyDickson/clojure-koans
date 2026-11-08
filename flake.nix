{
  description = "Clojure Koans development environment";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
  };

  outputs =
    { nixpkgs, ... }:
    let
      systems = [
        "aarch64-darwin"
        "aarch64-linux"
        "x86_64-linux"
      ];
    in
    {
      # `nix develop` from anywhere in the repository.
      devShells = nixpkgs.lib.genAttrs systems (
        system:
        let
          pkgs = nixpkgs.legacyPackages.${system};
        in
        {
          default = pkgs.mkShell {
            packages = with pkgs; [
              # Run the Clojure CLI on the same JDK as `java`, the current LTS.
              (clojure.override { jdk = jdk25; })
              jdk25
              # Readline editing and history for `just repl`
              rlwrap
              clojure-lsp # lsp
              # For linting and formatting Clojure source
              clj-kondo
              cljfmt
              # Task runner
              just
              # For formatting markdown
              dprint
              git
            ];
          };
        }
      );

      formatter = nixpkgs.lib.genAttrs systems (system: nixpkgs.legacyPackages.${system}.nixfmt-rfc-style);
    };
}
