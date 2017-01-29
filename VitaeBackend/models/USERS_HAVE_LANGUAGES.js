/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('USERS_HAVE_LANGUAGES', {
    language_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'LANGUAGES',
        key: 'language_id'
      }
    },
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    }
  }, {
    tableName: 'USERS_HAVE_LANGUAGES'
  });
};
